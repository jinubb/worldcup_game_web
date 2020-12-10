package com.wc.common;

import lombok.Getter;

@Getter
public enum Error {
	UnAuthorizedToken(1100, "미인증된 토큰 요청."),
	IlleagalArgument(1101, "적절한 파라미터가 아닙니다.");

	
	private int code;
	private String message;
	private Error(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
