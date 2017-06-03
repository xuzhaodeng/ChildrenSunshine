package com.pas.edu.service;

import java.util.List;

import com.pas.edu.entity.ChildRoster;

public interface ChildApplyService {
	
	void addChildRoster(ChildRoster childRoster); 
	
	
	void updateChildApply(ChildRoster childRoster);
	
	
	ChildRoster getRosterInfoByChildId(Integer childId);
	
	
	List<ChildRoster> getChildApplyLsts(Integer uid, Integer page, Integer pageSize);
	
	
	void delRoster(Integer uid, String childIds);
	
}
