package com.project.sonica.exceptionHandler;

public class TokenBlacklistedException extends RuntimeException {
	public TokenBlacklistedException(String message) {
		super(message);
	}
}
