package com.pas.edu.dao;

import com.pas.edu.entity.SafeguardRecord;
import org.apache.ibatis.annotations.Param;

public interface SafeguardRecordDao {
    SafeguardRecord getSafeguardRecord(@Param("safeguardId") int safeguardId);
    void createSafeguardRecord(SafeguardRecord safeguardRecord);
}
