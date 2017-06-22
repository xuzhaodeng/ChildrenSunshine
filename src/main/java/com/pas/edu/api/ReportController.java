package com.pas.edu.api;

import com.pas.edu.entity.ApplyReport;
import com.pas.edu.entity.ApplyStatusReport;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.ChildApplyService;
import com.pas.edu.service.OrganService;
import com.pas.edu.service.ReportService;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @Autowired
    private OrganService organService;

    @Autowired
    private ChildApplyService childApplyService;

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

    @ApiOperation(value = "数据采集准确率", notes = "数据采集准确率，统计下级机构的准确率")
    @ApiImplicitParams( value = {
            @ApiImplicitParam(paramType = "query", name = "orgId", value = "机构id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "beginTime", value = "开始时间", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endTime", value = "结束时间", required = false, dataType = "String")
    }
    )
    @RequestMapping(value = "accuracyRating", method = RequestMethod.GET)
    public BaseResult<List<EfficiencyResponse>> accuracyRating(@RequestParam(value = "orgId", required = true) Integer orgId, @RequestParam(value = "beginTime", required = false) String beginTime, @RequestParam(value = "endTime", required = false) String endTime) throws Exception {
        BaseResult<List<EfficiencyResponse>> result = new BaseResult<List<EfficiencyResponse>>();
        List<EfficiencyResponse> list = new ArrayList<EfficiencyResponse>();
        //查找子机构
        Organ organ = organService.getOrganDetail(orgId);
        List<Organ> organList = organ.getChildOrganList();
        for(Organ item:organList) {
            EfficiencyResponse response = new EfficiencyResponse();
            int notRefuseNum = childApplyService.getNotRefuseNum(orgId,beginTime,endTime);
            int allApplyNum = childApplyService.getAllApplyNum(orgId,beginTime,endTime);
            if(allApplyNum!=0) {
                response.setEfficiency(notRefuseNum/allApplyNum * 100+"%");
            }
            response.setOrgId(item.getOrgId());
            response.setOrgName(item.getOrgName());
        }
        result.setData(list);
        return result;
    }

    /**
     * 准确率、完成率请求返回
     */
    @Data
    class EfficiencyResponse {
        @ApiModelProperty("机构id")
        int orgId;
        @ApiModelProperty("机构名称")
        String orgName;
        @ApiModelProperty("百分比")
        String efficiency;
    }
}
