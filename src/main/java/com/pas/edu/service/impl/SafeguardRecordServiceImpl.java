package com.pas.edu.service.impl;

import com.pas.edu.common.DictionaryHelper;
import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.dao.OrganDao;
import com.pas.edu.dao.SafeguardRecordDao;
import com.pas.edu.entity.*;
import com.pas.edu.service.SafeguardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SafeguardRecordServiceImpl implements SafeguardRecordService{

    @Autowired
    private SafeguardRecordDao safeguardRecordDao;
    @Autowired
    private OrganDao organDao;
    @Autowired
    private ChildApplyDao childApplyDao;

    public void createSafeguardRecord(SafeguardRecord safeguardRecord) {
        safeguardRecordDao.createSafeguardRecord(safeguardRecord);
    }

    @Override
    public void updateSafeguardRecord(SafeguardRecord safeguardRecord) {
        safeguardRecordDao.updateSafeguardRecord(safeguardRecord);
    }

    @Override
    public SafeguardRecord getSafeguardRecord(int safeguardRecord) {
        return safeguardRecordDao.getSafeguardRecord(safeguardRecord);
    }

    @Override
    public List<SafeguardRecord> findSafeguardRecordList(Integer orgId, String beginTime, String endTime) {
        return safeguardRecordDao.findSafeguardRecordList(orgId, beginTime, endTime);
    }

    @Override
    public List<SafeguardReport> getSafeguardReport(int orgId, String beginTime, String endTime) {
        //获取子机构
        List<Organ> childOrgan = organDao.getChildOrganList(orgId);
        List<SafeguardReport> list = new ArrayList<SafeguardReport>();
        //遍历子机构的汇总
        for (Organ organ : childOrgan) {
            SafeguardReport summary = new SafeguardReport();
            summary.setOrgId(organ.getOrgId());
            summary.setOrgName(organ.getOrgName());

            List<SafeguardRecord> safeguardRecordList = safeguardRecordDao.findSafeguardRecordListByOrg(organ.getOrgId(),organ.getOrgLevel(), beginTime, endTime);
            //List<ChildRoster> childRosterList = childApplyDao.getChildByOrg(organ.getOrgId(), organ.getOrgLevel(), currLevel);
            //孤儿
            int orphanCount = 0;
            //特困儿童
            int provertyCount = 0;
            //重病伤残儿童
            int disabilityCount = 0;
            //其他困境儿童
            int otherCount = 0;
            int total = safeguardRecordList.size();
            summary.setTotal(total);
            //基本保障
            int baseProtectCount = 0;
            int baseNotProtectCount = 0;
            //教育保障
            int eduProtectCount = 0;
            int eduNotProtectCount = 0;
            //基本医疗保障
            int medicalProtectCount = 0;
            int medicalNotProtectCount = 0;
            //监护责任
            int custodyCount = 0;
            int custodyNotCount = 0;
            //残疾儿童福利
            int disabilityWelfareCount = 0;
            int disabilityNotWelfareCount = 0;
            for (SafeguardRecord safeguardRecord : safeguardRecordList) {
                ChildRoster childRoster = childApplyDao.getRosterInfoByChildId(safeguardRecord.getChildId());
                String dilemmaCategory = childRoster.getDilemmaCategory();
                if (DictionaryHelper.INFO_DILEMMA_CATEGORY_ORPHAN.equals(dilemmaCategory)) {
                    orphanCount++;
                } else if (DictionaryHelper.INFO_DILEMMA_CATEGORY_PROVERTY.equals(dilemmaCategory)) {
                    provertyCount++;
                } else if (DictionaryHelper.INFO_DILEMMA_CATEGORY_DISABILITY.equals(dilemmaCategory)) {
                    disabilityCount++;
                }
                if ("2".equals(childRoster.getGuaHappening())) {
                    custodyNotCount++;
                }
                if ("2".equals(safeguardRecord.getEducationHappening())) {
                    eduNotProtectCount++;
                }
                if ("2".equals(safeguardRecord.getMedicalHappening())) {
                    medicalNotProtectCount++;
                }
                if ("2".equals(safeguardRecord.getLifeHappening())) {
                    baseNotProtectCount++;
                }
                if ("2".equals(safeguardRecord.getWelfareHappening())) {
                    disabilityNotWelfareCount++;
                }
            }
            summary.setOrphanCount(orphanCount);

            summary.setProvertyCount(provertyCount);
            summary.setDisabilityCount(disabilityCount);
            summary.setOtherDifficultCount(total - orphanCount - provertyCount - disabilityCount);
            //
            summary.setBaseProtectCount(total - baseNotProtectCount);
            summary.setBaseNotProtectCount(baseNotProtectCount);
//
            summary.setEduProtectCount(total - eduNotProtectCount);
            summary.setEduNotProtectCount(eduNotProtectCount);
//
            summary.setMedicalProtectCount(total - medicalNotProtectCount);
            summary.setMedicalNotProtectCount(medicalNotProtectCount);
//
            summary.setCustodyCount(total - custodyNotCount);
            summary.setCustodyNotCount(custodyNotCount);
            //
            summary.setDisabilityWelfareCount(total - disabilityNotWelfareCount);
            summary.setDisabilityNotWelfareCount(disabilityNotWelfareCount);
            list.add(summary);
        }
        return list;
    }
}
