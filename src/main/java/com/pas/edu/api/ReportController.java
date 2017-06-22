package com.pas.edu.api;

import com.pas.edu.entity.ApplyReport;
import com.pas.edu.entity.ApplyStatusReport;
import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: eric
 * CreateDate: 2017/6/3
 * Modified: eric
 * ModifiedDate: 2017/6/3
 * Email: ericli_wang@163.com
 * Version: 1.0
 * Desc:
 */
@Api(value = "报表", tags = "报表接口")
@RestController
@RequestMapping("api/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @ApiOperation(value = "儿童统计报表", notes = "获取子机构下儿童信息的统计")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "orgId", dataType = "int", required = true, value = "机构ID"),
		@ApiImplicitParam(paramType = "query", name = "currLevel", dataType = "int", required = true, value = "当前登录级别 1、市 2、县 3、镇")
	})
    @RequestMapping(value = "applyReportList", method = RequestMethod.GET)
    public BaseResult<List<ApplyReport>> applyReportList(@RequestParam(value = "orgId", required = true) Integer orgId, 
			@RequestParam(value = "currLevel", required = true) Integer currLevel) throws Exception {
        BaseResult<List<ApplyReport>> result = new BaseResult<List<ApplyReport>>();
        result.setData(reportService.getApplyReport(orgId, currLevel));
        return result;
    }


    @ApiOperation(value = "机构审核状态", notes = "获取当前机构审核状态")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "orgId", dataType = "int", required = true, value = "机构ID"),
		@ApiImplicitParam(paramType = "query", name = "currLevel", dataType = "int", required = true, value = "当前登录级别 1、市 2、县 3、镇")
	})
    @RequestMapping(value = "applyStatus", method = RequestMethod.GET)
    public BaseResult<ApplyStatusReport> getApplyStatus(@RequestParam(value = "orgId", required = true) Integer orgId, 
			@RequestParam(value = "currLevel", required = true) Integer currLevel) throws Exception {
        BaseResult<ApplyStatusReport> result = new BaseResult<ApplyStatusReport>();
        result.setData(reportService.getApplyStatusReport(orgId, currLevel));
        return result;
    }

    //public BaseResult accuracy
}
