package com.pas.edu.service.impl;

import com.pas.edu.dao.VisitRecordDao;
import com.pas.edu.entity.VisitRecord;
import com.pas.edu.service.VisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitRecordServiceImpl implements VisitRecordService{

    @Autowired
    private VisitRecordDao visitRecordDao;

    public void createVisitRecord(VisitRecord visitRecord) {
        visitRecordDao.createVisitRecord(visitRecord);
    }

    @Override
    public VisitRecord getVisitRecord(int visitId) {
        return visitRecordDao.getVisitRecord(visitId);
    }
}
