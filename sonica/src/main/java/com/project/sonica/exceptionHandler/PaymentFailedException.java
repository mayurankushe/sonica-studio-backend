package com.project.sonica.exceptionHandler;

public class PaymentFailedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentFailedException(String transactionId) {
        super("Payment failed for transaction: " + transactionId);
    }
}
