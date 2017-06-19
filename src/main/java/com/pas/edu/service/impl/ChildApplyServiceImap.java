package com.pas.edu.service.impl;

import java.text.ParseException;
import java.util.List;

import com.pas.edu.dao.OrganDao;
import com.pas.edu.dao.UserDao;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.User;
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
	@Autowired
	OrganDao organDao;
	@Autowired
	UserDao userDao;

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
	public List<ChildRoster> getChildApplyLsts(Integer uid, Integer currPage, Integer pageSize) {
		Page<ChildRoster> page = PageHelper.startPage(currPage * pageSize, pageSize);
		List<ChildRoster> resultLsts = cpDao.getChildApplyLsts(uid);
		return resultLsts;
	}
	
	@Override
	public List<ChildRoster> getChildApplyLstsByOrgId(Integer orgId, Integer loginUserId, Integer currPage, Integer pageSize) {
		User user = userDao.getUserById(loginUserId);
		Organ organ = organDao.getOrgan(user.getOrgId());
		Page<ChildRoster> page = PageHelper.startPage(currPage * pageSize, pageSize);

		List<ChildRoster> resultLsts = cpDao.getChildApplyLstsByOrgid(orgId,organ.getOrgLevel());
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
