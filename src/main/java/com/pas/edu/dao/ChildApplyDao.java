package com.pas.edu.dao;

import com.pas.edu.entity.ChildRoster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChildApplyDao {

	void insertChildApply(ChildRoster childRoster);


	void updateChildApply(ChildRoster childRoster);


	List<ChildRoster> getChildByOrg(@Param("orgId") int orgId ,
										 @Param("orgLevel") int orgLevel);

}
