package com.xuz.myproject.spcbasedata.persist;

import java.util.function.Supplier;

/**
 * 操作数据库的基础服务抽象类
 *
 * @author daiwl
 */
public abstract class AbstractPersistBaseService {

    protected abstract PersistDataAccessTemplate persistDataAccessTemplate();

    /**
     * 执行 带有分片键的数据库操作
     *
     * @param param    传入的参数对象
     * @param dbAction 数据库的操作
     * @param <T>      泛型参数，返回结果的对象类型
     */
    public <T> T executePartition(Object param, Supplier<T> dbAction) {
        return persistDataAccessTemplate().execute(param, dbAction);
    }

    /**
     * 执行不含分片键的数据库操作
     *
     * @param action 数据库操作
     * @param <T>    泛型参数，返回结果的对象类型
     * @return 返回结果
     */
    public <T> T execute(Supplier<T> action) {
        return persistDataAccessTemplate().execute(action);
    }

}
