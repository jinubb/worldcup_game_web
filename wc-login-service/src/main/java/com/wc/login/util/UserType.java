package com.wc.login.util;

public enum UserType {
	ADMIN("AD"),
	USER("US"),
	UNKNOWN("");
	
	private final String code;
	private UserType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static UserType toType(String code) {
		switch(code) {
			case "US":
				return USER;
			case "AD":
				return ADMIN;
			default:
				throw new RuntimeException(String.format("존재하지 않는 유저타입 코드 '%s' 입니다.", code));
		}
	}
	
	
	public static boolean isAdminType(String code) {
		switch(code) {
			case "AD":
				return true;
			default:
				return false;
		}
	}
	
	public static String getRoleNameByType(UserType type) {
		switch(type) {
			case USER:
				return "User";
			case ADMIN:
				return "Admin";
			default:
				throw new RuntimeException(String.format("존재하지 않는 유저타입 코드 '%s' 입니다.", type.getCode()));
		}
	}
}
