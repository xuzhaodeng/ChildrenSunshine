package com.pas.edu.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.dao.SafeguardDao;
import com.pas.edu.entity.Safegrard;
import com.pas.edu.entity.SafeguardInfo;
import com.pas.edu.entity.SafeguardList;
import com.pas.edu.service.SafeguardService;
import com.pas.edu.utils.CommUtil;

@Service
public class SafeguardServiceImpl implements SafeguardService {
	
	@Autowired
	SafeguardDao sfdao;
	
	@Autowired
	ChildApplyDao cpDao;

	@Override
	public List<SafeguardList> getSafeuardLst(Integer uid, String searchTime) {
		List<SafeguardList> result = new ArrayList<SafeguardList>();
		String currTime = CommUtil.getDateFormat().substring(0, 7);
		List<Map<String, Object>> sfLst = sfdao.getSafeuardLst(uid, currTime);
		if(sfLst != null && sfLst.size() > 0){
			Integer sfNum = 0;
			for (Map<String, Object> map : sfLst) {
				SafeguardList safe = new SafeguardList();
				safe.setChildId(Integer.parseInt(map.get("childId").toString()));
				safe.setChildName(map.get("childName").toString());
				if(Integer.parseInt(map.get("educationHappening").toString()) == 1){
					sfNum ++;
				}
				
				if(Integer.parseInt(map.get("guardHappening").toString()) == 1){
					sfNum ++;
				}
				
				if(Integer.parseInt(map.get("lifeHappening").toString()) == 1){
					sfNum ++;
				}
				
				if(Integer.parseInt(map.get("welfareHappening").toString()) == 1){
					sfNum ++;
				}
				
				if(Integer.parseInt(map.get("medicalHappening").toString()) == 1){
					sfNum ++;
				}
				safe.setSafeguardNum(sfNum);
				safe.setStatus(Integer.parseInt(map.get("villageStatus").toString()));
				safe.setSafeguardId(Integer.parseInt(map.get("safeguardId").toString()));
				result.add(safe);
			}
		} else { //如果当前月没有保障评估记录
			List<Map<String, Object>> childLst = sfdao.getByChildLst(uid);
			if(childLst != null && childLst.size() > 0){
				for (Map<String, Object> map : childLst) {
					SafeguardList safe = new SafeguardList();
					safe.setChildId(Integer.parseInt(map.get("child_id").toString()));
					safe.setChildName(map.get("child_name").toString());
					safe.setSafeguardNum(0);
					safe.setStatus(2);
					safe.setSafeguardId(0);
					result.add(safe);
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println();
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
			sfinfo.setTownStatus(0);;
			sfinfo.setVillageStatus(2);;
			sfId = sfdao.insertSafeguard(sfinfo);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfId;
	}

	@Override
	public Integer updateSafeuard(Safegrard sfinfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SafeguardInfo getSafeguardInfo(Integer sfId) {
		SafeguardInfo sfInfo = sfdao.getSafeguardInfo(sfId);
		sfInfo.setChildRoster(cpDao.getRosterInfoByChildId(sfInfo.getChildId()));
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
