package com.pas.edu.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pas.edu.entity.Safegrard;
import com.pas.edu.entity.SafeguardInfo;

public interface SafeguardDao {
	
	public List<Map<String, Object>> getByChildLst(@Param("uid") Integer uid);
	
	public List<Map<String, Object>> getSafeuardLst(@Param("uid") Integer uid, @Param("currTime") String currTime);
	
	public SafeguardInfo getSafeguardInfo(@Param("sfId") Integer sfId);
	
	public Integer insertSafeguard(Safegrard sfinfo);
	
	public Integer updateSafeguard(Safegrard sfinfo);
	
	public Integer setSafAlrCommit(@Param("sfId") Integer sfId, @Param("uid") Integer uid, @Param("updTime") Date updTime);

}
