package com.pas.edu.service;

import com.pas.edu.entity.ChildRoster;

public interface ChildApplyService {
	
	void addChildRoster(ChildRoster childRoster);

	ChildRoster getRosterInfoByChildId(int childId);
	
	void updateChildApply(ChildRoster childRoster);
	
	
}
