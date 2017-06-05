package com.pas.edu.api;

import com.pas.edu.entity.ApplyReport;
import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @ApiImplicitParam(name = "orgId", paramType = "path", value = "机构id", required = true, dataType = "int")
    @RequestMapping(value = "applyReportList/{orgId}", method = RequestMethod.GET)
    public BaseResult<List<ApplyReport>> applyReportList(@PathVariable int orgId) throws Exception {
        BaseResult<List<ApplyReport>> result = new BaseResult<List<ApplyReport>>();
        result.setData(reportService.getApplyReport(orgId));
        return result;
    }


    @ApiOperation(value = "机构审核状态", notes = "获取当前机构审核状态")
    @ApiImplicitParam(name = "orgId", paramType = "path", value = "机构id", required = true, dataType = "int")
    @RequestMapping(value = "applyStatus/{orgId}", method = RequestMethod.GET)
    public BaseResult<Object> getApplyStatus(@PathVariable int orgId) throws Exception {
        Result result = new Result();
        result.setData(reportService.getApplyStatusReport(orgId));
        return result;
    }
}
