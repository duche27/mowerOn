package org.seat.exception;


public class Fault {

	private String resultDescription;
	private String result;

	public Fault(String resultDescription, String result) {
		this.resultDescription = resultDescription;
		this.result = result;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
