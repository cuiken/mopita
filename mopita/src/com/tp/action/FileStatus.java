package com.tp.action;

public enum FileStatus {
	OPEN("1"), CLOSE("0");
	private String value;

	private FileStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
