package com.xuz.myproject.spcbasedata.service;

import com.xuz.myproject.spcbasedata.persist.AbstractPersistBaseService;
import com.xuz.myproject.spcbasedata.persist.IPersistDataAccess;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 通用公共服务
 * 非分片表
 *
 * @param <T> 对象
 * @param <K> 主键
 */
public abstract class AbstractBaseService<T, K> implements IBaseService<T, K> {

    protected abstract IPersistDataAccess<T, K> getDao();
    protected abstract AbstractPersistBaseService getDataBaseService();

    @Override
    public void insert(T vo) {
        this.getDataBaseService().execute(() -> getDao().insert(vo));
    }

    @Override
    public void update(T vo) {
        this.getDataBaseService().execute(() -> getDao().update(vo));
    }

    @Override
    public List<T> query(T vo) {
        return this.getDataBaseService().execute(() -> getDao().query(vo));
    }

    @Override
    public T queryOne(T vo) {
        List<T> list = this.query(vo);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public T load(K id) {
        return this.getDataBaseService().execute(() -> getDao().load(id));
    }

    @Override
    public T loadOne(T vo) {
        return this.getDataBaseService().execute(() -> getDao().loadOne(vo));
    }

    @Override
    public void delete(K id) {
        this.getDataBaseService().execute(() -> getDao().delete(id));
    }

    @Override
    public void insertList(List<T> list) {

    }
}
