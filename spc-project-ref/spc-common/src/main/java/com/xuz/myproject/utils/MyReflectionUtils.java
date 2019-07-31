package com.xuz.myproject.utils;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 反射Util
 *
 * @author daiwl
 */
public abstract class MyReflectionUtils {

    private static final Field[] NO_FIELDS = {};

    private static final Map<Class<?>, Field[]> declaredAllFieldsCache = new ConcurrentHashMap<>(256);

    /**
     * 获取指定类以及其父类的所有字段
     *
     * @param clazz 需要获取的类的class文件
     * @return field数组
     */
    public static Field[] getAllDeclaredFields(Class<?> clazz) {
        Field[] result = declaredAllFieldsCache.get(clazz);
        if (result == null) {
            Field[] declaredFields = clazz.getDeclaredFields();
            List<Field> fields = new ArrayList<>(Arrays.asList(declaredFields));
            while (clazz.getSuperclass() != null) {
                clazz = clazz.getSuperclass();
                Field[] parentFields = clazz.getDeclaredFields();
                if (parentFields != null && parentFields.length > 0) {
                    fields.addAll(Arrays.asList(parentFields));
                }
            }
            result = fields.toArray(new Field[0]);
            declaredAllFieldsCache.put(clazz, (result.length == 0 ? NO_FIELDS : result));
        }
        return result;
    }

    /**
     * Clear the internal method/field cache
     */
    public static void clearCache() {
        declaredAllFieldsCache.clear();
    }


    /**
     * 基于get set间的copy，打印相关语句
     *
     * @param sourceClass      源对象get对象
     * @param targetClass      目标对象set对象
     * @param ignoreProperties 需要忽略属性值
     */
    public static void printCopy(Class sourceClass, Class targetClass, String... ignoreProperties) {
        printCopy(null, sourceClass, null, targetClass, ignoreProperties);
    }

    /**
     * 基于get set间的copy，打印相关语句
     *
     * @param sourceName       源对象get对象名称
     * @param sourceClass      源对象get对象
     * @param targetName       目标对象set对象名称
     * @param targetClass      目标对象set对象
     * @param ignoreProperties 需要忽略属性值
     */
    public static void printCopy(String sourceName, Class sourceClass, String targetName, Class targetClass, String... ignoreProperties) {
        CaseFormat fromFormat = CaseFormat.LOWER_CAMEL;
        CaseFormat toFormat = CaseFormat.UPPER_CAMEL;

        sourceName = StringUtils.isEmpty(sourceName) ? toFormat.to(fromFormat, sourceClass.getSimpleName()) : sourceName;
        targetName = StringUtils.isEmpty(targetName) ? toFormat.to(fromFormat, targetClass.getSimpleName()) : targetName;

        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        String finalTargetName = targetName;
        String finalSourceName = sourceName;
        ReflectionUtils.doWithFields(targetClass, field -> {
            if (ignoreList != null && !ignoreList.contains(field.getName())) {
                Field sourceField = ReflectionUtils.findField(sourceClass, field.getName());
                if (sourceField != null) {
                    String sentence = String.format("%s.set%s(%s.get%s());", finalTargetName, fromFormat.to(toFormat, field.getName()), finalSourceName, fromFormat.to(toFormat, sourceField.getName()));
                    System.out.println(sentence);
                }
            }
        }, field -> !Objects.equals("serialVersionUID", field.getName()));
    }

    /**
     * @param obj 对象
     * @param map map
     * @author huanghuan
     * @description key-value 赋值给对应对象
     * @date 2018-11-13 17:14
     */
    public static void copyValue(Object obj, Map<String, Object> map) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object data = map.get(field.getName());
                if (data != null) {
                    field.set(obj, data);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static int tiems = 0;
    private static long time_consuming = 0;

    /**
     * @param service 服务对象
     * @param methodName  方法名称
     * @param params      参数
     * @return
     */
    public static Object springInvokeMethod(Object service, String methodName, Object[] params) {
        if (service != null) {
            Class[] paramClass = null;
            if (params != null && params.length > 0 && params[0] != null)
                paramClass = Arrays.stream(params).map(Object::getClass).collect(Collectors.toList()).toArray(new Class[params.length]);
            // 找到方法
            Method method = ReflectionUtils.findMethod(service.getClass(), methodName, paramClass);
            AssertUtil.notNull(method, String.format("找不到类[%s]的方法[%s]", service.getClass().getName(), methodName));
            // 执行方法
            return ReflectionUtils.invokeMethod(method, service, params);
        }
        return null;
    }

}
