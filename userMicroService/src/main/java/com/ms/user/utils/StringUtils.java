package com.ms.user.utils;

/**
 * @author Pedro Ferreira
 **/
public abstract class StringUtils {
	
	public static boolean isNullOrEmpty(String text) {
		if (text == null || text.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNullOrEmpty(String text) {
		if (text != null && !text.isEmpty()) {
			return true;
		}
		return false;
	}
}
