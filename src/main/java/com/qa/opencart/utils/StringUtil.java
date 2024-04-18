package com.qa.opencart.utils;

public class StringUtil {

	public static String getRandomEmail() {
		String randomEmailid="automation"+System.currentTimeMillis()+"@opencart.com";
		return randomEmailid;
	}
}
