package com.pas.edu.service.impl;

import com.pas.edu.dao.AuditRecordDao;
import com.pas.edu.entity.AuditRecord;
import com.pas.edu.service.AuditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditRecordServiceImpl implements AuditRecordService{

    @Autowired
    private AuditRecordDao auditRecordDao;

    public void createAuditRecord(AuditRecord auditRecord) {
        //auditRecordDao.
    }
}
