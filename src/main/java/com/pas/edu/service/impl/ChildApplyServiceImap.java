package com.pas.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.service.ChildApplyService;

public class ChildApplyServiceImap implements ChildApplyService {
	
	@Autowired
	ChildApplyDao cpDao;

	@Override
	public void addChildRoster(ChildRoster childRoster) {
		cpDao.insertChildApply(childRoster);
	}

	@Override
	public void updateChildApply(ChildRoster childRoster) {
		cpDao.updateChildApply(childRoster);
	}

}
