package com.jbrown.core.util;

public class StringUtil {
	public static boolean isEmpty(String s) {
		return (s == null) || s.trim().equals("");
	}

	public static boolean isEmpty(String... stringArray) {
		for (String s : stringArray) {
			if (isEmpty(s)) {
				return true;
			}
		}
		return false;
	}
}
