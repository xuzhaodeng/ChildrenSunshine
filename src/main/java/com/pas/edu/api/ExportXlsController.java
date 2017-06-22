package com.pas.edu.api;


import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pas.edu.common.DictionaryHelper;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.SafeguardRecord;
import com.pas.edu.entity.SafeguardReport;
import com.pas.edu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pas.edu.entity.common.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * execl导出
 * @author 邓子
 * @time 2017/6/7
 */
@Api(value = "execl导出", tags = "execl导出")
@RestController
@RequestMapping(value = "api/export")
public class ExportXlsController {
	
	@Autowired
	ExportPoiService epService;
	@Autowired
	private SafeguardRecordService safeguardRecordService;
	@Autowired
	private ChildApplyService childApplyService;
	@Autowired
	private DatadictService datadictService;

	/**
	 * 花名册信息导出
	 * @return
	 */
	@ApiOperation(value = "花名册信息导出", notes = "花名册信息导出")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "rosterId", dataType = "int", required = true, value = "花名册Id")
	})
	@RequestMapping(value = "/rosterInfo", method = RequestMethod.GET)
	public BaseResult<Object> exportRoster(@RequestParam(value = "rosterId", required = true) Integer rosterId,
			HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath(File.separator);
		BaseResult<Object> br = new BaseResult<Object>();
		br.setData(epService.getRosterById(rosterId));
		return br;
	}
	
	/**
	 * 花名册列表导出 -- 村下花名册列表
	 * @return
	 */
	@ApiOperation(value = "花名册列表导出", notes = "花名册列表导出")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "villId", dataType = "int", required = true, value = "导出村ID"),
		@ApiImplicitParam(paramType = "query", name = "currLevel", dataType = "int", required = false, value = "当前登录级别 1、市 2、县 3、镇")
	})
	@RequestMapping(value = "/rosterLsts", method = RequestMethod.GET)
	public BaseResult<Object> exportRosterLsts(@RequestParam(value = "villId", required = true) Integer villId,
			@RequestParam(value = "currLevel", required = false) Integer currLevel){
		BaseResult<Object> br = new BaseResult<Object>();
		br.setData(epService.getRosterLsts(villId, currLevel));
		return br;
	}
	
	/**
	 * 花名册统计报表导出
	 * @return
	 */
	@ApiOperation(value = "花名册统计报表导出", notes = "花名册统计报表导出")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "orgId", dataType = "int", required = true, value = "导出结构ID"),
		@ApiImplicitParam(paramType = "query", name = "currLevel", dataType = "int", required = true, value = "当前登录级别 1、市 2、县 3、镇")
	})
	@RequestMapping(value = "/statistical", method = RequestMethod.GET)
	public BaseResult<Object> exportStatisticalRoster(@RequestParam(value = "orgId", required = true) Integer orgId,
			@RequestParam(value = "currLevel", required = true) Integer currLevel){
		BaseResult<Object> br = new BaseResult<Object>();
		br.setData(epService.getSummaryLsts(orgId, currLevel));
		return br;
	}

	@ApiOperation(value = "花名册列表导出", notes = "花名册列表导出")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(paramType = "query", name = "orgId", value = "机构id", required = true, dataType = "int"),
			@ApiImplicitParam(paramType = "query", name = "beginTime", value = "开始时间", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "endTime", value = "结束时间", required = false, dataType = "String")
	})

	/**
	 * 导出评估保障统计报表
	 */
	@RequestMapping(value = "/exportSafeguardReports", method = RequestMethod.GET)
	public BaseResult<Object> exportSafeguardReports(@RequestParam(value = "orgId", required = true) Integer orgId,
													 @RequestParam(value = "beginTime", required = false) String beginTime, @RequestParam(value = "endTime", required = false) String endTime){
		BaseResult<Object> br = new BaseResult<Object>();
		List<SafeguardReport> safeguardReports = safeguardRecordService.getSafeguardReport(orgId, beginTime, endTime);
		br.setData(epService.getExportSafeguardReports(orgId, safeguardReports));
		return br;
	}

	/**
	 * 导出评估保障统计报表
	 */
	@RequestMapping(value = "/exportSafeguardLists", method = RequestMethod.GET)
	public BaseResult<Object> exportSafeguardLists(@RequestParam(value = "orgId", required = true) Integer orgId,
													 @RequestParam(value = "beginTime", required = false) String beginTime, @RequestParam(value = "endTime", required = false) String endTime){
		BaseResult<Object> br = new BaseResult<Object>();
		Map<String,String> jhqkMap = datadictService.getDatadictMap(DictionaryHelper.DATA_TYPE_JHQK);
		Map<String,String> kjlbMap = datadictService.getDatadictMap(DictionaryHelper.DATA_TYPE_KJLB);
		Map<String,String> jbshqkMap = datadictService.getDatadictMap(DictionaryHelper.DATA_TYPE_JBSHQK);
		Map<String,String> jyqkMap = datadictService.getDatadictMap(DictionaryHelper.DATA_TYPE_JYQK);
		Map<String,String> ylqkMap = datadictService.getDatadictMap(DictionaryHelper.DATA_TYPE_YLQK);
		Map<String,String> flqkMap = datadictService.getDatadictMap(DictionaryHelper.DATA_TYPE_FLQK);

		List<SafeguardRecord> safeguardRecordList = safeguardRecordService.findSafeguardRecordList(orgId, beginTime, endTime);
		for(SafeguardRecord safeguardRecord:safeguardRecordList) {
			ChildRoster childRoster = childApplyService.getRosterInfoByChildId(safeguardRecord.getChildId());
			childApplyService.decorateChildRoster(childRoster,jhqkMap,kjlbMap,jbshqkMap,jyqkMap,ylqkMap,flqkMap);
			safeguardRecord.setChildRoster(childRoster);
		}
		br.setData(epService.getExportSafeguardList(orgId, safeguardRecordList));

		return br;
	}
}
