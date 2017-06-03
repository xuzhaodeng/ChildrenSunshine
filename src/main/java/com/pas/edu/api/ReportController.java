package com.pas.edu.api;

import com.pas.edu.entity.Organ;
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

    @ApiOperation(value = "机构采集报表", notes = "")
    @ApiImplicitParam(name = "orgId", paramType = "path", value = "机构id", required = true, dataType = "int")
    @RequestMapping(value = "summaryList/{orgId}", method = RequestMethod.GET)
    public Result login(@PathVariable int orgId) throws Exception {
        Result result = new Result();
        result.setData(reportService.getSummaryList(orgId));
        return result;
    }
}
