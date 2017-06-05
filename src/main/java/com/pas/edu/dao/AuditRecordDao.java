package com.pas.edu.dao;

import com.pas.edu.entity.AuditRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuditRecordDao {
    AuditRecord getAuditRecord(@Param("id") int id);
    void createAuditRecord(AuditRecord auditRecord);
    List<AuditRecord> getAuditRecordList(@Param("applyId") int applyId);
}
