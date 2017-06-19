package com.pas.edu.service;


import com.pas.edu.entity.SafeguardRecord;

public interface SafeguardRecordService {
    public void createSafeguardRecord(SafeguardRecord safeguardRecord);
    public SafeguardRecord getSafeguardRecord(int safeguardId);
}
