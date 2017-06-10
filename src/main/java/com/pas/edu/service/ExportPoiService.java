package com.pas.edu.service;

import com.pas.edu.entity.ExportExcel;

//import com.pas.edu.entity.NameSheet;
//import com.pas.edu.entity.PoiChildRoster;
//import com.pas.edu.entity.Summary;

public interface ExportPoiService {
	
	//PoiChildRoster getRosterById(Integer childId);
	
	ExportExcel getRosterById(Integer childId);
	
	//List<NameSheet> getRosterLsts(Integer villId);
	
	ExportExcel getRosterLsts(Integer villId);
	
	//List<Summary> getSummaryLsts(Integer orgId);
	
	ExportExcel getSummaryLsts(Integer orgId, Integer currLevel);

}
