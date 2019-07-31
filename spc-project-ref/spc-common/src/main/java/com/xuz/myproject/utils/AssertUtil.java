package com.xuz.myproject.utils;

import com.xuz.myproject.constants.ServiceExceptionEnum;
import com.xuz.myproject.exception.ErrorCodeEnum;
import com.xuz.myproject.exception.MyIllegalArgumentException;
import com.xuz.myproject.exception.RequestParamException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;


/**
 * 断言工具类
 *
 * @author daiwl
 */
public class AssertUtil {

    /**
     * 对象为空抛出异常
     *
     * @param param
     * @param message
     */
    public static void notNull(Object param, String message) {
        if (param == null) {
            throw new MyIllegalArgumentException(ErrorCodeEnum.NOT_NULL, message);
        }
    }


    /**
     * 表达式为false抛出异常
     *
     * @param expression
     * @param message
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new MyIllegalArgumentException(ErrorCodeEnum.INVALID_PARAM, message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new MyIllegalArgumentException(ErrorCodeEnum.INVALID_PARAM, message);
        }
    }
    
    public static void notEmpty(Map<String, Object> map, String message) {
    	isTrue(MapUtils.isNotEmpty(map), message);
    }

    /**
     * 字符串为空抛出异常
     *
     * @param param
     * @param message
     */
    public static void notEmpty(String param, String message) {
        if (param == null || "".equals(param)) {
            throw new MyIllegalArgumentException(ErrorCodeEnum.INVALID_PARAM, message);
        }
    }
    
    /**
     * 校验客户是否已定位
     * @param obj
     * @param message
     */
    public static void checkCustExist(Object obj, String message) {
    	/*if (obj == null || (obj instanceof String && StringUtil.isBlank(StringUtil.valueOf(obj)))) {
    		throw new MyIllegalArgumentException(ErrorCodeEnum.NO_LOCATION_CUST, message);
    	}*/
    }

    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new RequestParamException(ServiceExceptionEnum.E_10001, message);
        }
    }

    public static void hasOne(Object object, String message1, String text, String message2) {
        if (object == null && !StringUtils.hasText(text)) {
            throw new RequestParamException(ServiceExceptionEnum.E_80001, message1 + "与" + message2);
        }
    }

    /**
     * @param array
     * @param message
     * @Description: TODO(查询详情 验证分页信息)
     */
    public static void isNotEmpty(Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new MyIllegalArgumentException(ErrorCodeEnum.NOT_NULL, message);
        }
    }

}
