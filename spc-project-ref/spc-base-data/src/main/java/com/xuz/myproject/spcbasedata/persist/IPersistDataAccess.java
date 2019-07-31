package com.xuz.myproject.spcbasedata.persist;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作数据库的通用接口，包含对原子对象的增删改查基础操作
 *
 * @param <T> 泛型参数，原子对象类型
 * @param <K> 泛型参数，原子对象主键类型
 */
public interface IPersistDataAccess<T, K> {

    /**
     * 插入
     *
     * @param obj 原子对象
     * @return 插入记录数
     */
    int insert(@Param("vo") T obj);

    /**
     * 更新
     *
     * @param obj 原子对象
     * @return 更新记录数
     */
    int update(@Param("vo") T obj);

    /**
     * 按主键删除
     *
     * @param id 主键
     * @return 删除记录数
     */
    int delete(@Param("rowId") K id);

    /**
     * 按主键查询
     *
     * @param id 主键
     * @return 查询出的结果
     */
    T load(@Param("rowId") K id);

    /**
     * 按主键查询
     *
     * @param param 查询条件
     * @return 查询出的结果
     */
    T loadOne(@Param("vo") T param);

    /**
     * 查询列表
     *
     * @param param 查询条件
     * @return 结果集
     */
    List<T> query(@Param("vo") T param);
}
