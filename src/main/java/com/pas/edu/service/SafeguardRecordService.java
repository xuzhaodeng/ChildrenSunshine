package com.pas.edu.service;


import com.pas.edu.entity.SafeguardRecord;
import com.pas.edu.entity.SafeguardReport;

import java.util.List;

public interface SafeguardRecordService {
    public void createSafeguardRecord(SafeguardRecord safeguardRecord);
    public void updateSafeguardRecord(SafeguardRecord safeguardRecord);
    public SafeguardRecord getSafeguardRecord(int safeguardId);
    public List<SafeguardRecord> findSafeguardRecordList(Integer orgId, String beginTime, String endTime);
    public List<SafeguardReport> getSafeguardReport(int orgId, String beginTime, String endTime);
}
