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

    /**
     * 应评估人数
     * @param orgId 机构id
     * @param orgLevel 机构级别
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return int
     */
    int getSafeguardNum(int orgId, int orgLevel, String beginTime, String endTime);

    /**
     * 花名册总人数
     * @param orgId 机构id
     * @param orgLevel 机构级别
     * @return
     */
    int getRosterNum(int orgId, int orgLevel);
}
