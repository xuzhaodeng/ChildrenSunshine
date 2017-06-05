package com.pas.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pas.edu.entity.ChildRoster;

public interface ChildApplyDao {

	void insertChildApply(ChildRoster childRoster);


	void updateChildApply(ChildRoster childRoster);
	
	
	ChildRoster getRosterInfoByChildId(@Param("childId") Integer childId);
	
	
	List<ChildRoster> getChildApplyLsts(@Param("uid") Integer uid);
	
	
	void delRoster(@Param("operateId") Integer uid, @Param("childId") Integer childId);


	List<ChildRoster> getChildByOrg(@Param("orgId") int orgId ,
										 @Param("orgLevel") int orgLevel);

}
