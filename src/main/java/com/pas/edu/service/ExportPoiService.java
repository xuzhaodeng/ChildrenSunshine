package com.pas.edu.service;

import java.util.List;

import com.pas.edu.entity.NameSheet;
import com.pas.edu.entity.PoiChildRoster;
import com.pas.edu.entity.Summary;

public interface ExportPoiService {
	
	PoiChildRoster getRosterById(Integer childId);
	
	List<NameSheet> getRosterLsts(Integer villId);
	
	List<Summary> getSummaryLsts(Integer orgId);

}
