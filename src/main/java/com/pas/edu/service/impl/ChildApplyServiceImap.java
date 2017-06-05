package com.pas.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.service.ChildApplyService;
import com.pas.edu.utils.CommUtil;

@Service
public class ChildApplyServiceImap implements ChildApplyService {
	
	@Autowired
	ChildApplyDao cpDao;

	@Override
	public void addChildRoster(ChildRoster childRoster) {
		childRoster.setCreateTime(CommUtil.getDateFormat());
		childRoster.setUpdateTime(CommUtil.getDateFormat());
		cpDao.insertChildApply(childRoster);
	}

	@Override
	public ChildRoster getRosterInfoByChildId(int childId) {
		return cpDao.getRosterInfoByChildId(childId);
	}

	@Override
	public void updateChildApply(ChildRoster childRoster) {
		
		
		cpDao.updateChildApply(childRoster);
	}

}
