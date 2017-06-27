package com.pas.edu.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.dao.SafeguardDao;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.CompleteOrgan;
import com.pas.edu.entity.Safegrard;
import com.pas.edu.entity.SafeguardInfo;
import com.pas.edu.entity.SafeguardList;
import com.pas.edu.service.OrganService;
import com.pas.edu.service.SafeguardService;
import com.pas.edu.utils.CommUtil;

@Service
public class SafeguardServiceImpl implements SafeguardService {
	
	@Autowired
	SafeguardDao sfdao;
	
	@Autowired
	ChildApplyDao cpDao;
	
	@Autowired
	OrganService orginService;
	
	@Value("${imagePath}")
	private String imagePath;

	@Override
	public List<SafeguardList> getSafeuardLst(Integer uid, String searchTime) {
		List<SafeguardList> result = new ArrayList<SafeguardList>();
		String currTime = CommUtil.getDateFormat().substring(0, 7);
		
		List<Map<String, Object>> childLst = sfdao.getByChildLst(uid); //审核通过的困境儿童列表
		if(childLst != null && childLst.size() > 0){
			for (Map<String, Object> map : childLst) {
				SafeguardList safe = new SafeguardList();
				safe.setChildId(Integer.parseInt(map.get("child_id").toString()));
				safe.setChildName(map.get("child_name").toString());
				Integer sfTotalNum = 0;
				Integer sfNum = 0;
				Integer status = 2;
				Integer safeguardId = 0;
				
				if(map.get("medical_happening").toString() != "0" && map.get("medical_happening").toString() != null && map.get("medical_happening").toString() != ""){
					sfTotalNum ++;
				}
				
				if(map.get("education_happening").toString() != "0" && map.get("education_happening").toString() != null && map.get("education_happening").toString() != ""){
					sfTotalNum ++;	
				}
				
				if(map.get("basic_life").toString() != "0" && map.get("basic_life").toString() != null && map.get("basic_life").toString() != ""){
					sfTotalNum ++;
				}
				
				if(map.get("welfare").toString() != "0" && map.get("welfare").toString() != null && map.get("welfare").toString() != ""){
					sfTotalNum ++;
				}
				
				if(map.get("guardian_hapening").toString() != "0" && map.get("guardian_hapening").toString() != null && map.get("guardian_hapening").toString() != ""){
					sfTotalNum ++;
				}
				
				safe.setSafeguardNum(sfTotalNum);
				List<Map<String, Object>> safeLst = sfdao.getSafeuardByChildId(Integer.parseInt(map.get("child_id").toString()), currTime);
				if(safeLst != null && safeLst.size() > 0){
					if(Integer.parseInt(safeLst.get(0).get("educationHappening").toString()) == 1){
						sfNum ++;
					}
					
					if(Integer.parseInt(safeLst.get(0).get("guardHappening").toString()) == 1){
						sfNum ++;
					}
					
					if(Integer.parseInt(safeLst.get(0).get("lifeHappening").toString()) == 1){
						sfNum ++;
					}
					
					if(Integer.parseInt(safeLst.get(0).get("welfareHappening").toString()) == 1){
						sfNum ++;
					}
					
					if(Integer.parseInt(safeLst.get(0).get("medicalHappening").toString()) == 1){
						sfNum ++;
					}
					
					status = Integer.parseInt(safeLst.get(0).get("villageStatus").toString());
					safeguardId = Integer.parseInt(safeLst.get(0).get("safeguardId").toString());
				}
				safe.setSafeguardId(safeguardId);
				safe.setAlreadySafeguardNum(sfNum);
				safe.setStatus(status);
				result.add(safe);
			}
		}
	
		return result;
	}

	@Override
	public Integer insertSafeuard(Safegrard sfinfo) {
		Integer sfId = 0;
		try {
			Date currDate = CommUtil.getDateFormat(CommUtil.getDateFormat());
			sfinfo.setCreateTime(currDate);
			sfinfo.setUpdateTime(currDate);
			sfinfo.setCityStatus(0);
			sfinfo.setCountyStatus(0);
			sfinfo.setTownStatus(0);
			sfinfo.setVillageStatus(2);
			
			 CompleteOrgan co =  orginService.getCompleteOrgan(sfinfo.getCreatorId());
             if(co != null){
            	 sfinfo.setVillageId(co.getVillageOrg().getOrgId());
            	 sfinfo.setVillageName(co.getVillageOrg().getOrgName());
                  
            	 sfinfo.setTownId(co.getTownOrg().getOrgId());
            	 sfinfo.setTownName(co.getTownOrg().getOrgName());
                  
            	 sfinfo.setCountyId(co.getCountyOrg().getOrgId());
            	 sfinfo.setCountyName(co.getCountyOrg().getOrgName());
                  
            	 sfinfo.setCityId(co.getCityOrg().getOrgId());
            	 sfinfo.setCityName(co.getCityOrg().getOrgName());
                  
             }
			sfId = sfdao.insertSafeguard(sfinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sfId > 0 ? sfinfo.getSafeguardId() : 0;
	}

	@Override
	public Integer updateSafeuard(Safegrard sfinfo) {
		try {
			 CompleteOrgan co =  orginService.getCompleteOrgan(sfinfo.getCreatorId());
             if(co != null){
            	 sfinfo.setVillageId(co.getVillageOrg().getOrgId());
            	 sfinfo.setVillageName(co.getVillageOrg().getOrgName());
                  
            	 sfinfo.setTownId(co.getTownOrg().getOrgId());
            	 sfinfo.setTownName(co.getTownOrg().getOrgName());
                  
            	 sfinfo.setCountyId(co.getCountyOrg().getOrgId());
            	 sfinfo.setCountyName(co.getCountyOrg().getOrgName());
                  
            	 sfinfo.setCityId(co.getCityOrg().getOrgId());
            	 sfinfo.setCityName(co.getCityOrg().getOrgName());
                  
             }
			sfinfo.setUpdateTime(CommUtil.getDateFormat(CommUtil.getDateFormat()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sfdao.updateSafeguard(sfinfo);
	}

	@Override
	public SafeguardInfo getSafeguardInfo(Integer sfId) {
		SafeguardInfo sfInfo = sfdao.getSafeguardInfo(sfId);
		ChildRoster cr = cpDao.getRosterInfoByChildId(sfInfo.getChildId());
		cr.setHeadImgPath(imagePath);
		sfInfo.setChildRoster(cr);
		return sfInfo;
	}

	@Override
	public Integer setSafAlrCommit(Integer sfId, Integer uid) {
		Integer rows = 0;
		try {
			rows = sfdao.setSafAlrCommit(sfId, uid, CommUtil.getDateFormat(CommUtil.getDateFormat()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

}
