package com.project.sonica.security.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.sonica.apiResponseWrapper.ApiResponse;
import com.project.sonica.exceptionHandler.TokenBlacklistedException;
import com.project.sonica.exceptionHandler.TokenExpiredException;
import com.project.sonica.security.JwtUtil;
import com.project.sonica.security.RegisterRequest;
import com.project.sonica.security.User;
import com.project.sonica.security.UserService;
import com.project.sonica.security.token.BlacklistService;
import com.project.sonica.security.token.RefreshToken;
import com.project.sonica.security.token.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private BlacklistService blacklistService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest request) {
		User user = userService.registerUser(request);
		return ResponseEntity.ok(new ApiResponse<>(200, "User registered successfully", user.getUsername()));
	}

//	@PostMapping("/login")
//	public ResponseEntity<ApiResponse<String>> login(@RequestBody AuthRequest request) {
//		authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//		final String jwt = jwtUtil.generateToken(userDetails);
//
//		return ResponseEntity.ok(new ApiResponse<>(200, "Login successful", jwt));
//	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody AuthRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String jwt = jwtUtil.generateAccessToken(userDetails);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getUsername());

		Map<String, String> tokens = new HashMap<>();
		tokens.put("accessToken", jwt);
		tokens.put("refreshToken", refreshToken.getToken());

		return ResponseEntity.ok(new ApiResponse<>(200, "Login successful", tokens));
	}

//	Rotating Refresh Endpoint
//	 Benefits
//	 - Stronger security → stolen refresh tokens can’t be reused.
//	 - Revocable → refresh tokens stored in DB, can be deleted on logout.
//	 - Rotation policy → aligns with best practices (OAuth2, OpenID Connect).
//	 - Clean flow → client always receives a fresh pair of tokens

	@PostMapping("/refresh")
	public ResponseEntity<ApiResponse<Map<String, String>>> refresh(@RequestBody Map<String, String> request) {
		String oldRefreshTokenStr = request.get("refreshToken");

		if (blacklistService.isBlacklisted(oldRefreshTokenStr)) {
			throw new TokenBlacklistedException("Refresh token is invalidated");
		}

		RefreshToken oldRefreshToken = refreshTokenService.findByToken(oldRefreshTokenStr)
				.map(refreshTokenService::verifyExpiration)
				.orElseThrow(() -> new TokenExpiredException("Refresh token expired"));

		UserDetails userDetails = userDetailsService.loadUserByUsername(oldRefreshToken.getUsername());
		String newAccessToken = jwtUtil.generateAccessToken(userDetails);

		RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());
		blacklistService.blacklistToken(oldRefreshTokenStr, oldRefreshToken.getExpiryDate());

		Map<String, String> tokens = new HashMap<>();
		tokens.put("accessToken", newAccessToken);
		tokens.put("refreshToken", newRefreshToken.getToken());

		return ResponseEntity.ok(new ApiResponse<>(200, "Token refreshed successfully", tokens));
	}
	
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, @RequestParam String newPassword) {
        try {
            userService.resetPassword(email, newPassword);
            return ResponseEntity.ok("Password reset successful!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
