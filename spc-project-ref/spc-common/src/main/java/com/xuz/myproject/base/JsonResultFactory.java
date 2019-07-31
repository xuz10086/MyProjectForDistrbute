package com.xuz.myproject.base;

import com.xuz.myproject.constants.Constants;

public class JsonResultFactory {

    public static JsonResult setResultError(Integer code,String msg) {
        return setResult(code, msg, null);
    }
    // 返回错误，可以传msg
    public static JsonResult setResultError(String msg) {
        return setResult(Constants.HTTP_RES_CODE_500, msg, null);
    }

    // 返回成功，可以传data值
    public static JsonResult setResultSuccess(Object data) {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
    }

    // 返回成功，沒有data值
    public static JsonResult setResultSuccess() {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
    }

    // 返回成功，沒有data值
    public static JsonResult setResultSuccess(String msg) {
        return setResult(Constants.HTTP_RES_CODE_200, msg, null);
    }

    // 通用封装
    public static JsonResult setResult(Integer code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }
}
