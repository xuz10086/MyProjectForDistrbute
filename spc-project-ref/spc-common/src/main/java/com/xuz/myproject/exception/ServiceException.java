package com.xuz.myproject.exception;


import com.xuz.myproject.constants.ServiceExceptionEnum;

/**
 * 服务异常
 */
public class ServiceException extends RuntimeException{
	
	private static final long serialVersionUID = 6096999649229551562L;
	
	private String resultCode;
	private String resultMsg;
	
	public ServiceException(){
		super();
	}
	
	public ServiceException(String resultMsg){
		super();
		this.resultMsg = resultMsg;
	}
	
	public ServiceException(String resultCode,String resultMsg){
		super();
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	
	public ServiceException(ServiceExceptionEnum resultEnum){
		super();
		this.resultCode = resultEnum.getResultCode();
		this.resultMsg = resultEnum.getResultMsg();
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
