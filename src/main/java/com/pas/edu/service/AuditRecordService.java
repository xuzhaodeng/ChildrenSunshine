package com.pas.edu.service;


import com.pas.edu.entity.AuditRecord;

import java.util.List;

public interface AuditRecordService {
    public void createAuditRecord(AuditRecord auditRecord);
    public List<AuditRecord> getAuditRecordList(int applyId);
}
