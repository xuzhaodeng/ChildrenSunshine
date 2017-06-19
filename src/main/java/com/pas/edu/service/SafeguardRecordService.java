package com.pas.edu.service;


import com.pas.edu.entity.SafeguardRecord;

import java.util.List;

public interface SafeguardRecordService {
    public void createSafeguardRecord(SafeguardRecord safeguardRecord);
    public void updateSafeguardRecord(SafeguardRecord safeguardRecord);
    public SafeguardRecord getSafeguardRecord(int safeguardId);
    public List<SafeguardRecord> findSafeguardRecordList(Integer orgId);
}
