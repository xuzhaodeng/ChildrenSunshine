package com.pas.edu.dao;

import com.pas.edu.entity.AuditRecord;
import org.apache.ibatis.annotations.Param;

public interface AuditRecordDao {
    AuditRecord getAuditRecord(@Param("id") int id);
    //createAuditRecord
}
