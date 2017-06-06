package com.pas.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.dao.HistoryRosterDao;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.service.HistoryRosterService;

@Service
public class HistoryRosterServiceImpl implements HistoryRosterService {
	
	@Autowired
	HistoryRosterDao hrDao;
	
	@Autowired
	ChildApplyDao caDao;

	@Override
	public void insertHistoryRoster(Integer childId) {
		
		ChildRoster cr = caDao.getRosterInfoByChildId(childId); 
		hrDao.insertHistoryRoster(cr);
		
	} 
	

}
