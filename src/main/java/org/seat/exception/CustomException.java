package org.seat.exception;


public class CustomException extends Exception {

	final Fault fault = new Fault(null, "KO");

	public CustomException(String failure) {
		this.fault.setResultDescription(failure);
	}

	public CustomException(Exception e) {
		this.fault.setResultDescription(e.getMessage());
	}

	public Fault getFault() {
		return fault;
	}
}
