package com.pas.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pas.edu.entity.ChildRoster;

public interface ChildApplyDao {

	Integer insertChildApply(ChildRoster childRoster);


	void updateChildApply(ChildRoster childRoster);
	
	
	ChildRoster getRosterInfoByChildId(@Param("childId") Integer childId);
	
	
	List<ChildRoster> getChildApplyLsts(@Param("uid") Integer uid);
	
	List<ChildRoster> getChildApplyLstsByOrgid(@Param("orgId") Integer orgId, @Param("level") Integer level, @Param("userLevel") Integer userLevel);
	
	
	void delRoster(@Param("operateId") Integer uid, @Param("childId") Integer childId);
	
	Object getRosterByChildIdCard(@Param("idCard") String idCard);


	List<ChildRoster> getChildByOrg(@Param("orgId") int orgId, @Param("orgLevel") int orgLevel, @Param("currLevel") int currLevel);

	ChildRoster getRosterInfoByChildId(@Param("childId") int childId);

	int getNotRefuseNum(@Param("orgId") int orgId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	int getAllApplyNum(@Param("orgId") int orgId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
