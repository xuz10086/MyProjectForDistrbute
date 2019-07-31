package com.xuz.myproject.utils;

import com.xuz.myproject.constants.Constants;

import java.util.UUID;

public class TokenUtils {

	public static String getToken(String prefix) {
		return prefix + "-" + UUID.randomUUID();
	}

	public static String getMemberToken() {
		return Constants.TOKEN_MEMBER + "-" + UUID.randomUUID();
	}

	public static String getPayToken() {
		return Constants.TOKEN_PAY + "-" + UUID.randomUUID();
	}
}
