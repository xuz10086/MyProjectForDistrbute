package com.xuz.myproject.exception;

/**
 * 上传图片错误异常类
 * 
 * @author 王辉
 * 
 * @Version 修改时间:2018年10月16日,上午11:52:08
 */
public class UploadErrorException extends GlobalException {
	/**
	 * @author 王辉
	 * 
	 * @Version 修改时间:2018年10月16日,上午11:52:28
	 */
	private static final long serialVersionUID = -5014970251639077040L;

	public UploadErrorException() {
		super();
	}

	public UploadErrorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UploadErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public UploadErrorException(String message) {
		super(message);
	}

	public UploadErrorException(Throwable cause) {
		super(cause);
	}

}
