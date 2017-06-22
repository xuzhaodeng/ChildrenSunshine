package com.pas.edu.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pas.edu.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pas.edu.common.DictionaryHelper;
import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.dao.ExportPoiDao;
import com.pas.edu.dao.OrganDao;
import com.pas.edu.service.ExportPoiService;
import com.pas.edu.utils.ExcelUtil;

@Service
public class ExportPoiServiceImpl implements ExportPoiService {
	
	@Value("${export.execlPath}")
	private String path;
	
	@Value("${export.execl.rosterInfoName}")
	private String rosInfoName;
	
	@Value("${export.execl.rosterLstName}")
	private String rosLstName;
	
	@Value("${export.execl.statisticalName}")
	private String rosStatName;
	
	@Value("${export.execl.suffix}")
	private String execlSuffix;
	
	@Value("${export.excel.url}")
	private String fileUrl;
	
	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	@Autowired
	ExportPoiDao exportDao;
	
	@Autowired
    ChildApplyDao childApplyDao;

    @Autowired
    OrganDao organDao;

	@Override
	public ExportExcel getRosterById(Integer childId) {
		String dateName = fmt.format(new Date());
		String fileName = path + dateName + "." + execlSuffix;
		PoiChildRoster pcr =  exportDao.getRosterById(childId); //
		try {
			ExcelUtil.exportDetailExcel(pcr, new FileOutputStream(new File(fileName)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ExportExcel(fileUrl + "/" + dateName + "." + execlSuffix , rosInfoName);
	}

	@Override
	public ExportExcel getRosterLsts(Integer villId, Integer currLevel) {
		String dateName = fmt.format(new Date());
		String fileName = path + dateName + "." + execlSuffix;
		Organ org = organDao.getOrgan(villId);
		List<NameSheet> nsLsts =  exportDao.getRosterLsts(villId, currLevel); //new FileOutputStream(new File("D://困境儿童列表.xls"))
		try {
			ExcelUtil.exportRoster(new FileOutputStream(new File(fileName)), nsLsts, org.getOrgName());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ExportExcel(fileUrl + "/" + dateName + "." + execlSuffix , rosLstName);
	}

    @Override
    public ExportExcel getExportSafeguardReports(int orgId, List<SafeguardReport> safeguardReports) {
        String dateName = fmt.format(new Date());
        String fileName = path + dateName + "." + execlSuffix;
        Organ org = organDao.getOrgan(orgId);
        try {
            ExcelUtil.exportSafeguardReports(new FileOutputStream(new File(fileName)), safeguardReports, org.getOrgName());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ExportExcel(fileUrl + "/" + dateName + "." + execlSuffix , rosLstName);
    }

	@Override
	public ExportExcel getSummaryLsts(Integer orgId, Integer currLevel) {
		String dateName = fmt.format(new Date());
		String fileName = path + dateName + "." + execlSuffix;
		//获取子机构
        List<Organ> childOrgan = organDao.getChildOrganList(orgId);
        List<Summary> list = new ArrayList<Summary>();
        //遍历子机构的汇总
        for (Organ organ : childOrgan) {
            Summary summary = new Summary();
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
            summary.setTotal(total);
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
        
        Organ org = organDao.getOrgan(orgId);
        try { //new FileOutputStream(new File("D://困境儿童统计表.xls"))
			ExcelUtil.exportTotalExcel(new FileOutputStream(new File(fileName)), org.getOrgName(), list);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new ExportExcel(fileUrl + "/" + dateName + "." + execlSuffix , rosStatName);
	}

}
