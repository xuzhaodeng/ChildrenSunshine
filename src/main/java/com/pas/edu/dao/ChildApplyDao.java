package com.pas.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pas.edu.entity.ChildRoster;

public interface ChildApplyDao {

	void insertChildApply(ChildRoster childRoster);


	void updateChildApply(ChildRoster childRoster);
	
	
	ChildRoster getRosterInfoByChildId(@Param("childId") Integer childId);
	
	
	List<ChildRoster> getChildApplyLsts(@Param("uid") Integer uid);
	
	List<ChildRoster> getChildApplyLstsByOrgid(@Param("villageId") Integer villageId);
	
	
	void delRoster(@Param("operateId") Integer uid, @Param("childId") Integer childId);
	
	Object getRosterByChildIdCard(@Param("idCard") String idCard);


	List<ChildRoster> getChildByOrg(@Param("orgId") int orgId, @Param("orgLevel") int orgLevel);

	ChildRoster getRosterInfoByChildId(@Param("childId") int childId);
}
