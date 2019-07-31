package com.xuz.myproject.exception;

import com.xuz.myproject.base.JsonResult;
import com.xuz.myproject.base.JsonResultFactory;
import com.xuz.myproject.constants.Constants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一处理Controller中抛出的异常
 *
 * @author xuz
 * @date 2019/7/17 10:56 AM
 * @param
 * @return
 */
@ControllerAdvice
public class ApplicationControllerExceptionHander {

    @ExceptionHandler
    @ResponseBody
    public JsonResult handlerException(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        if (e instanceof GlobalException) {
            return JsonResultFactory.setResultError(Constants.HTTP_RES_CODE_401, e.getMessage());
        }
        return JsonResultFactory.setResultError("操作失败，服务器未知异常");
    }
}
