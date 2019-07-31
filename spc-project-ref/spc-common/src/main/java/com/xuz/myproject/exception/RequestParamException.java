package com.xuz.myproject.exception;


import com.xuz.myproject.constants.ServiceExceptionEnum;

/**
 * 
 * 功能说明：接口请求参数异常
 * 
 * @author 
 *
 * 
 *
 */
public class RequestParamException extends ServiceException{

private static final long serialVersionUID = 4044512726591135043L;
	
	public RequestParamException(){
		super();
	}
	
	public RequestParamException(ServiceExceptionEnum resultEnum){
		super();
		this.setResultCode(resultEnum.getResultCode());
		this.setResultMsg(resultEnum.getResultMsg());
	}
	
	public RequestParamException(ServiceExceptionEnum resultEnum, String msg){
		super();
		this.setResultCode(resultEnum.getResultCode());
		this.setResultMsg(String.format(resultEnum.getResultMsg(),msg));
	}
}
