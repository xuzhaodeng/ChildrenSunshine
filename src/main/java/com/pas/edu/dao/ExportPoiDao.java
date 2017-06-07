package com.pas.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pas.edu.entity.NameSheet;
import com.pas.edu.entity.PoiChildRoster;

public interface ExportPoiDao {
	
	PoiChildRoster getRosterById(@Param("childId") Integer childId);
	
	List<NameSheet> getRosterLsts(@Param("villId") Integer villId);
}
