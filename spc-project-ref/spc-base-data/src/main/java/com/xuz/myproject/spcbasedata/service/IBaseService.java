package com.xuz.myproject.spcbasedata.service;

import java.util.List;

/**
 * 提供一系列基础服务
 *
 * @param <T> 泛型参数，原子对象类型
 * @param <K> 泛型参数，原子对象主键类型
 */
public interface IBaseService<T, K> {
    /**
     * 插入
     *
     * @param vo 对象
     */
    void insert(T vo);

    /**
     * 更新
     *
     * @param vo 对象
     */
    void update(T vo);

    /**
     * 查询
     *
     * @param vo 对象
     * @return 查询出的结果集
     */
    List<T> query(T vo);

    /**
     * 查询
     * 基于query方法
     *
     * @param vo 对象
     * @return 查询出结果集获取第一条数据
     */
    T queryOne(T vo);

    /**
     * 查询
     * 数据库层直接限制查询一条
     *
     * @param vo 对象
     * @return 查询返回一条数据
     */
    T loadOne(T vo);

    /**
     * 按主键查询
     *
     * @param id 主键
     * @return 查询出的结果
     */
    T load(K id);

    /**
     * 根据主键id删除
     *
     * @param id 主键id
     */
    void delete(K id);

    void insertList(List<T> list);
}
