package com.pas.edu.service.impl;

import com.pas.edu.dao.SafeguardRecordDao;
import com.pas.edu.entity.SafeguardRecord;
import com.pas.edu.service.SafeguardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SafeguardRecordServiceImpl implements SafeguardRecordService{

    @Autowired
    private SafeguardRecordDao safeguardRecordDao;

    public void createSafeguardRecord(SafeguardRecord safeguardRecord) {
        safeguardRecordDao.createSafeguardRecord(safeguardRecord);
    }

    @Override
    public void updateSafeguardRecord(SafeguardRecord safeguardRecord) {
        safeguardRecordDao.updateSafeguardRecord(safeguardRecord);
    }

    @Override
    public SafeguardRecord getSafeguardRecord(int safeguardRecord) {
        return safeguardRecordDao.getSafeguardRecord(safeguardRecord);
    }

    @Override
    public List<SafeguardRecord> findSafeguardRecordList(Integer orgId) {
        return safeguardRecordDao.findSafeguardRecordList(orgId);
    }
}
