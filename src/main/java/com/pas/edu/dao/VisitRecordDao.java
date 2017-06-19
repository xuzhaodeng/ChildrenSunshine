package com.pas.edu.dao;

import com.pas.edu.entity.VisitRecord;
import org.apache.ibatis.annotations.Param;

public interface VisitRecordDao {
    VisitRecord getVisitRecord(@Param("visitId") int visitId);
    void createVisitRecord(VisitRecord visitRecord);
}
