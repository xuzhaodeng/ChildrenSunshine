package com.pas.edu.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.service.ChildApplyService;
import com.pas.edu.utils.CommUtil;

@Service
public class ChildApplyServiceImap implements ChildApplyService {
	
	@Autowired
	ChildApplyDao cpDao;

	@Override
	public Integer addChildRoster(ChildRoster childRoster) {
		try {
			childRoster.setCreateTime(CommUtil.getDateFormat(CommUtil.getDateFormat()));
			childRoster.setUpdateTime(CommUtil.getDateFormat(CommUtil.getDateFormat()));
			Integer rows = cpDao.insertChildApply(childRoster);
			return childRoster.getChildId();
		} catch (ParseException e) {
			return 0;
		}
		
	}

	@Override
	public ChildRoster getRosterInfoByChildId(int childId) {
		return cpDao.getRosterInfoByChildId(childId);
	}

	@Override
	public void updateChildApply(ChildRoster childRoster){
		try {
			childRoster.setUpdateTime(CommUtil.getDateFormat(CommUtil.getDateFormat()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpDao.updateChildApply(childRoster);
	}

	@Override
	public ChildRoster getRosterInfoByChildId(Integer childId) {
		return cpDao.getRosterInfoByChildId(childId);
	}

	@Override
	public List<ChildRoster> getChildApplyLsts(Integer uid, Integer currPage, Integer pageSize) {
		Page<ChildRoster> page = PageHelper.startPage(currPage * pageSize, pageSize);
		List<ChildRoster> resultLsts = cpDao.getChildApplyLsts(uid);
		return resultLsts;
	}
	
	@Override
	public List<ChildRoster> getChildApplyLstsByOrgId(Integer orgId, Integer currPage, Integer pageSize) {
		Page<ChildRoster> page = PageHelper.startPage(currPage * pageSize, pageSize);
		List<ChildRoster> resultLsts = cpDao.getChildApplyLstsByOrgid(orgId);
		return resultLsts;
	}

	@Override
	public void delRoster(Integer uid, String childIds) {
		if(childIds != null){
			String [] childIdsArr =  childIds.split(",");
			if(childIdsArr != null && childIdsArr.length > 0){
				for (String childid : childIdsArr) {
					cpDao.delRoster(uid, Integer.parseInt(childid));
				}
			}
		}
	}

	@Override
	public Object getRosterByChildIdCard(String idCard) {
		return cpDao.getRosterByChildIdCard(idCard);
	}
	

}
