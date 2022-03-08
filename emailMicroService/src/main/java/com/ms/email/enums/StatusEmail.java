package com.ms.email.enums;

public enum StatusEmail {
	SENT ("S"), 
	ERROR ("E");

	private String value;

	StatusEmail(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
