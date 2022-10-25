package org.seat.domain.exceptions;


public class CustomException extends Exception {

	final Fault fault = new Fault(null, "KO");

	public CustomException(String failure) {
		super(failure);
		this.fault.setResultDescription(failure);
	}

	public CustomException(Exception e) {
		super(e.getMessage());
		this.fault.setResultDescription(e.getMessage());
	}

	public Fault getFault() {
		return fault;
	}
}
