package com.project.sonica.exceptionHandler;


public class BookingNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookingNotFoundException(Integer bookingId) {
		super("Booking not found with ID: " + bookingId);
	}
}
