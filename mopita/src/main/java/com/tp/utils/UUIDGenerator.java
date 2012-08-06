package com.tp.utils;

import java.util.UUID;

public class UUIDGenerator {

	private UUIDGenerator() {
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}
}
