package com.xuz.myproject.constants;

/**
 * 
 * 功能说明：服务异常枚举
 * 
 * @author
 *
 *
 *
 */
public enum ServiceExceptionEnum {
	SYSTEM_EXCEPTION("-1", "系统异常"),
	SUCCESS("0","处理成功"),
	E_10001("10001","必填参数%s未传入"),
	E_90001("90001","%s参数异常"),
	E_90002("90002","请新建参与人再进行校验"),
	E_90003("90003","校验不通过"),
	E_80001("80001","参数%s必传其一"),
	E_90004("90004","%s参数不在服务服务内");
	
	private String resultCode;
	private String resultMsg;

	private ServiceExceptionEnum(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

}
