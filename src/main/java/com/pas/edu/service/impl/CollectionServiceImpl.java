package com.pas.edu.service.impl;

import com.pas.edu.dao.CollectionDao;
import com.pas.edu.entity.ApplyChildRequest;
import com.pas.edu.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: eric
 * CreateDate: 2017/6/3
 * Modified: eric
 * ModifiedDate: 2017/6/3
 * Email: ericli_wang@163.com
 * Version: 1.0
 * Desc:
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionDao collectionDao;

    @Override
    public void save(ApplyChildRequest applyChildRequest) {
        collectionDao.save(applyChildRequest);
    }
}
