package com.pas.edu.dao;

import com.pas.edu.entity.SafeguardRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SafeguardRecordDao {
    SafeguardRecord getSafeguardRecord(@Param("safeguardId") int safeguardId);
    void createSafeguardRecord(SafeguardRecord safeguardRecord);
    void updateSafeguardRecord(SafeguardRecord safeguardRecord);
    List<SafeguardRecord> findSafeguardRecordList(@Param("villageId") Integer villageId);
}
