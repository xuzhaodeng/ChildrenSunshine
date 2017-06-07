package com.pas.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pas.edu.common.DictionaryHelper;
import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.dao.ExportPoiDao;
import com.pas.edu.dao.OrganDao;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.NameSheet;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.PoiChildRoster;
import com.pas.edu.entity.Summary;
import com.pas.edu.service.ExportPoiService;

@Service
public class ExportPoiServiceImpl implements ExportPoiService {
	
	@Autowired
	ExportPoiDao exportDao;
	
	@Autowired
    ChildApplyDao childApplyDao;

    @Autowired
    OrganDao organDao;

	@Override
	public PoiChildRoster getRosterById(Integer childId) {
		return exportDao.getRosterById(childId);
	}

	@Override
	public List<NameSheet> getRosterLsts(Integer villId) {
		return exportDao.getRosterLsts(villId);
	}

	@Override
	public List<Summary> getSummaryLsts(Integer orgId) {
		//获取子机构
        List<Organ> childOrgan = organDao.getChildOrganList(orgId);
        List<Summary> list = new ArrayList<Summary>();
        //遍历子机构的汇总
        for (Organ organ : childOrgan) {
            Summary summary = new Summary();
            summary.setOrgId(organ.getOrgId());
            summary.setOrgName(organ.getOrgName());
            List<ChildRoster> childRosterList = childApplyDao.getChildByOrg(organ.getOrgId(), organ.getOrgLevel());
            //孤儿
            int orphanCount = 0;
            //特困儿童
            int provertyCount = 0;
            //重病伤残儿童
            int disabilityCount = 0;
            //其他困境儿童
            int otherCount = 0;
            int total = childRosterList.size();
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

}
