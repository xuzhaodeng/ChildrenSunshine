package com.pas.edu.service;

import com.pas.edu.entity.ExportExcel;
import com.pas.edu.entity.SafeguardReport;

import java.util.List;

//import com.pas.edu.entity.NameSheet;
//import com.pas.edu.entity.PoiChildRoster;
//import com.pas.edu.entity.Summary;

public interface ExportPoiService {
	
	//PoiChildRoster getRosterById(Integer childId);
	
	ExportExcel getRosterById(Integer childId);
	
	//List<NameSheet> getRosterLsts(Integer villId);
	
	ExportExcel getRosterLsts(Integer villId, Integer currLevel);
	
	//List<Summary> getSummaryLsts(Integer orgId);
	
	ExportExcel getSummaryLsts(Integer orgId, Integer currLevel);

	/**
	 * 导出评估保障统计报表
	 * @param orgId
	 * @param safeguardReports
	 * @return
	 */
	ExportExcel getExportSafeguardReports(int orgId, List<SafeguardReport> safeguardReports);
}
