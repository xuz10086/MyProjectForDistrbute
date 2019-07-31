package com.xuz.myproject.spcbasedata.service;

import com.xuz.myproject.spcbasedata.persist.AbstractPersistBaseService;
import com.xuz.myproject.spcbasedata.persist.PersistDataAccessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 提供默认数据库操作服务
 */
@Component
public class DefualtPersistBaseService extends AbstractPersistBaseService {

    @Autowired
    private PersistDataAccessTemplate persistDataAccessTemplate;

    @Override
    protected PersistDataAccessTemplate persistDataAccessTemplate() {
        return persistDataAccessTemplate;
    }

    /*public void setPersistDataAccessTemplate(PersistDataAccessTemplate persistDataAccessTemplate) {
        this.persistDataAccessTemplate = persistDataAccessTemplate;
    }*/
}
