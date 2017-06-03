package com.pas.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.User;
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
	public void updateChildApply(ChildRoster childRoster) {
		childRoster.setUpdateTime(CommUtil.getDateFormat());
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
		System.out.println("数组长度为: " + resultLsts.size());
		return resultLsts;
	}
	

}
