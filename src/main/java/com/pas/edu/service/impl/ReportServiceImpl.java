package com.pas.edu.service.impl;

import com.pas.edu.common.DictionaryHelper;
import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.dao.OrganDao;
import com.pas.edu.entity.ApplyStatusReport;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.ApplyReport;
import com.pas.edu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: eric
 * CreateDate: 2017/6/3
 * Modified: eric
 * ModifiedDate: 2017/6/3
 * Email: ericli_wang@163.com
 * Version: 1.0
 * Desc:
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ChildApplyDao childApplyDao;

    @Autowired
    OrganDao organDao;


    /**
     * 儿童申请报表
     *
     * @param orgId
     * @return
     * @throws Exception
     */
    @Override
    public List<ApplyReport> getApplyReport(int orgId, int currLevel) throws Exception {
        //获取子机构
        List<Organ> childOrgan = organDao.getChildOrganList(orgId);
        List<ApplyReport> list = new ArrayList<ApplyReport>();
        //遍历子机构的汇总
        for (Organ organ : childOrgan) {
            ApplyReport summary = new ApplyReport();
            summary.setOrgId(organ.getOrgId());
            summary.setOrgName(organ.getOrgName());
            List<ChildRoster> childRosterList = childApplyDao.getChildByOrg(organ.getOrgId(), organ.getOrgLevel(), currLevel);
            //孤儿
            int orphanCount = 0;
            //特困儿童
            int provertyCount = 0;
            //重病伤残儿童
            int disabilityCount = 0;
            //其他困境儿童
            int otherCount = 0;
            int total = childRosterList.size();
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
            for (ChildRoster childRoster : childRosterList) {
                String dilemmaCategory = childRoster.getDilemmaCategory();
                if (DictionaryHelper.INFO_DILEMMA_CATEGORY_ORPHAN.equals(dilemmaCategory)) {
                    orphanCount++;
                } else if (DictionaryHelper.INFO_DILEMMA_CATEGORY_PROVERTY.equals(dilemmaCategory)) {
                    provertyCount++;
                } else if (DictionaryHelper.INFO_DILEMMA_CATEGORY_DISABILITY.equals(dilemmaCategory)) {
                    disabilityCount++;
                }
                if ("0".equals(childRoster.getGuaHappening())) {
                    custodyNotCount++;
                }
                if ("0".equals(childRoster.getEducationHappening())) {
                    eduNotProtectCount++;
                }
                if ("0".equals(childRoster.getMedicalHappening())) {
                    medicalNotProtectCount++;
                }
                if ("0".equals(childRoster.getBasicLifeHappening())) {
                    baseNotProtectCount++;
                }
                if ("0".equals(childRoster.getWelfareHappening())) {
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

    /**
     * 审核状态报表
     *
     * @param orgId
     * @return
     * @throws Exception
     */
    @Override
    public ApplyStatusReport getApplyStatusReport(int orgId, int currLevel) throws Exception {
        ApplyStatusReport report = new ApplyStatusReport();
        report.setOrgId(orgId);
        Organ organ = organDao.getOrgan(orgId);
        //过去该机构下的所有申请列表
        List<ChildRoster> childRosterList = childApplyDao.getChildByOrg(organ.getOrgId(), organ.getOrgLevel(), currLevel);
        //待审核
        int notAuditCount = 0;
        //审核中
        int inAuditCount = 0;
        //通过
        int passCount = 0;
        //拒绝
        int refuseCount = 0;
        for (ChildRoster childRoster : childRosterList) {
            int status = 0;
            // TODO: 2017/6/3 根据机构级别获取当前机构的状态信息
            switch (organ.getOrgLevel()) {
                case 1:
                    status = childRoster.getVillageStatus();
                    break;
                case 2:
                    status = childRoster.getTownStatus();
                    break;
                case 3:
                    status = childRoster.getCountyStatus();
                    break;
                case 4:
                    status = childRoster.getCityStatus();
                    break;
            }
            if (status == DictionaryHelper.APPLY_NOT_AUDIT) {
                notAuditCount++;
            } else if (status == DictionaryHelper.APPLY_IN_AUDIT) {
                inAuditCount++;
            } else if (status == DictionaryHelper.APPLY_PASS) {
                passCount++;
            } else {
                refuseCount++;
            }
        }
        report.setNotAuditCount(notAuditCount);
        report.setInAuditCount(inAuditCount);
        report.setPassCount(passCount);
        report.setRefuseCount(refuseCount);
        return report;
    }
}
