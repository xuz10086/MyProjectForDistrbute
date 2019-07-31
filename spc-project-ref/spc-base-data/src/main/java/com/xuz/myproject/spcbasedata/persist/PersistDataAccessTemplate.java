package com.xuz.myproject.spcbasedata.persist;


import com.alibaba.fastjson.JSON;
import com.xuz.myproject.exception.ErrorCodeEnum;
import com.xuz.myproject.exception.MyIllegalArgumentException;
import com.xuz.myproject.exception.NoValidPartitionKeyOrIndexException;
import com.xuz.myproject.utils.MyReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Supplier;

/**
 * 操作数据库的模板
 *
 * @author daiwl
 */
@Component
public class PersistDataAccessTemplate {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 执行数据操作
     *
     * @param action 实例操作
     * @param <T>    泛型参数，实例执行后的返回对象类型
     * @return 实例执行后的返回结果
     */
    public <T> T execute(Supplier<T> action) {
        return action.get();
    }

    /**
     * 执行分布式数据库的操作，需要传入分片键或者分片索引
     *
     * @param param  如传String Long Integer 当分片索引分片键处理，other反射对象中的参数
     * @param action 实例操作
     * @param <T>    泛型参数，dao实例执行后的返回对象类型
     * @return 执行返回结果
     */
    public <T> T execute(Object param, Supplier<T> action) {
        if (param instanceof String || param instanceof Long || param instanceof Integer) {
            if (param instanceof String && StringUtils.isBlank(param.toString())) {
                throw new NoValidPartitionKeyOrIndexException(
                        ErrorCodeEnum.PARTITION_KEY_OR_INDEX, "没有传入有效的分片键或者分片索引");
            }
            return action.get();
        }
        if (param == null) {
            throw new MyIllegalArgumentException(ErrorCodeEnum.NOT_NULL, "入参对象不能为空");
        }
        Class clazz = param.getClass();
        Field[] fields = MyReflectionUtils.getAllDeclaredFields(clazz);
        boolean flag = false; //对象中是否有不为空的分片键或者分片索引的标志，false是没有，true是有
        if (fields != null) {
            for (Field field : fields) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                if (annotations != null) {
                    for (Annotation annotation : annotations) {
                        String annotationName = annotation.annotationType().getSimpleName();
                        if (annotationName.equals("PartitionKey") || annotationName.equals("PartitionIndex")) {
                            Object value = null;
                            try {
                                if (!field.isAccessible())
                                    field.setAccessible(true);
                                value = field.get(param);
                            } catch (IllegalAccessException e) {
                                //do nothing
                            }
                            if (value != null) {
                                if (value instanceof String) {
                                    if (!"".equals(value)) {
                                        flag = true;
                                    }
                                } else {
                                    flag = true;
                                }
                            }
                        }
                    }
                }
                if (flag)
                    break;
            }
        }
        if (!flag) {
            logger.warn("入参{" + JSON.toJSONString(param) + "}" + "类型" + clazz.getName());
            throw new NoValidPartitionKeyOrIndexException(
                    ErrorCodeEnum.PARTITION_KEY_OR_INDEX, "没有传入有效的分片键或者分片索引");
        }
        return action.get();
    }
}
