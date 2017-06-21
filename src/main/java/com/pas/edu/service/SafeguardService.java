package com.pas.edu.service;

import java.util.List;

import com.pas.edu.entity.Safegrard;
import com.pas.edu.entity.SafeguardInfo;
import com.pas.edu.entity.SafeguardList;

public interface SafeguardService {
	
	public List<SafeguardList> getSafeuardLst(Integer uid, String searchTime);
	
	public Integer insertSafeuard(Safegrard safegrard);
	
	public Integer updateSafeuard(Safegrard sfinfo);
	
	public SafeguardInfo getSafeguardInfo(Integer sfId);
	
	public Integer setSafAlrCommit(Integer sfId, Integer uid);
}
