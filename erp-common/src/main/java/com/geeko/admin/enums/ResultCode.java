package com.geeko.admin.enums;

/**
 * 调用系统服务的结果（状态）
 * 
 * @author wzj
 * @date 2015-3-13
 */
public enum ResultCode {

	SUCCESS(200, "成功"), UNAUTHORIZED(300, "未授权"), WARN_MESSAGE(400, "错误提示信息"), SYSTEM_ERROR(500,
			"系统错误"), ACCESS_TOKEN_EXPIRED(600, "access_token失效");

	public int code;
	private String name;

	ResultCode(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

	public static boolean isSuccess(int code) {
		return SUCCESS.code == code;
	}

	public static ResultCode getResultCode(int code) {
		for (ResultCode resultCode : ResultCode.values()) {
			if (resultCode.code == code)
				return resultCode;
		}
		return null;
	}

}
