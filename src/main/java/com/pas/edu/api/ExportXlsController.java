package com.pas.edu.api;


import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.service.ExportPoiService;
import com.pas.edu.service.OrganService;

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
		@ApiImplicitParam(paramType = "query", name = "villId", dataType = "int", required = true, value = "导出村ID")
	})
	@RequestMapping(value = "/rosterLsts", method = RequestMethod.GET)
	public BaseResult<Object> exportRosterLsts(@RequestParam(value = "villId", required = true) Integer villId){
		BaseResult<Object> br = new BaseResult<Object>();
		br.setData(epService.getRosterLsts(villId));
		return br;
	}
	
	/**
	 * 花名册统计报表导出
	 * @return
	 */
	@ApiOperation(value = "花名册统计报表导出", notes = "花名册统计报表导出")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "orgId", dataType = "int", required = true, value = "导出结构ID")
	})
	@RequestMapping(value = "/statistical", method = RequestMethod.GET)
	public BaseResult<Object> exportStatisticalRoster(@RequestParam(value = "orgId", required = true) Integer orgId){
		BaseResult<Object> br = new BaseResult<Object>();
		br.setData(epService.getSummaryLsts(orgId));
		return br;
	}

}
