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
	

}
