package com.pas.edu.dao;

import com.pas.edu.entity.SafeguardRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SafeguardRecordDao {
    SafeguardRecord getSafeguardRecord(@Param("safeguardId") int safeguardId);
    void createSafeguardRecord(SafeguardRecord safeguardRecord);
    void updateSafeguardRecord(SafeguardRecord safeguardRecord);
    List<SafeguardRecord> findSafeguardRecordList(@Param("villageId") Integer villageId, @Param("beginTime") String beginTime, @Param("endTime")  String endTime);
    List<SafeguardRecord> findSafeguardRecordListByOrg(@Param("orgId") Integer orgId, @Param("orgLevel") Integer orgLevel, @Param("beginTime") String beginTime, @Param("endTime")  String endTime);

    int getSafeguardNum(@Param("orgId") int orgId, @Param("orgLevel") int orgLevel, @Param("beginTime") String beginTime, @Param("endTime")  String endTime);

    int getRosterNum(@Param("orgId") int orgId, @Param("orgLevel") int orgLevel);
}
