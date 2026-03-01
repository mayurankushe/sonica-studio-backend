package com.project.sonica.dashboard;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminDashboardController {
	private final DashboardService dashboardService;

	public AdminDashboardController(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	@GetMapping("/dashboard")
	public ResponseEntity<Map<String, Object>> dashboard() {
		return ResponseEntity.ok(dashboardService.getAdminData());
	}
}
