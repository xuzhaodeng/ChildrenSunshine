package com.pas.edu.api;

import com.pas.edu.entity.AuditRecord;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.SafeguardRecord;
import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.service.ChildApplyService;
import com.pas.edu.service.SafeguardRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "保障评估接口", tags = "保障评估接口")
@RestController
@RequestMapping("api/safeguardRecord")
public class SafeguardRecordController extends BaseController{

    @Autowired
    private SafeguardRecordService safeguardRecordService;
    @Autowired
    private ChildApplyService childApplyService;

    @ApiOperation(value = "保障评估记录列表", notes = "列出保障评估记录信息")
    @ApiImplicitParam(paramType = "query", name = "orgId", value = "机构id", required = true, dataType = "int")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public BaseResult<List<SafeguardRecord>> list(@RequestParam(value = "orgId", required = true) Integer orgId) {
        List<SafeguardRecord> safeguardRecordList = safeguardRecordService.findSafeguardRecordList(orgId);
        for(SafeguardRecord safeguardRecord:safeguardRecordList) {
            ChildRoster childRoster = childApplyService.getRosterInfoByChildId(safeguardRecord.getChildId());
            safeguardRecord.setChildRoster(childRoster);
        }
        BaseResult result = new BaseResult();
        result.setData(safeguardRecordList);
        return result;
    }

    @ApiOperation(value = "添加保障评估记录", notes = "添加保障评估记录")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseResult save(@RequestBody SafeguardRecord safeguardRecord) {
        BaseResult result = new BaseResult();
        safeguardRecordService.createSafeguardRecord(safeguardRecord);
        result.setData(safeguardRecord.getSafeguardId());
        return result;
    }

    @ApiOperation(value = "更新保障评估记录", notes = "更新保障评估记录")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public BaseResult update(@RequestBody SafeguardRecord safeguardRecord) {
        BaseResult result = new BaseResult();
        SafeguardRecord old = safeguardRecordService.getSafeguardRecord(safeguardRecord.getSafeguardId());
        //old.setChildRoster();
        //old.setChildId();
        //old.setCityStatus();
        //old.setCountyStatus();
        //old.setCreateTime();
        //old.setCreatorId();
        old.setDescription(safeguardRecord.getDescription());
        old.setEducationHappening(safeguardRecord.getEducationHappening());
        old.setEducationHappeningDesc(safeguardRecord.getEducationHappeningDesc());
        old.setGuaranteeStatus(safeguardRecord.getGuaranteeStatus());
        old.setGuaranteeStatusDesc(safeguardRecord.getGuaranteeStatusDesc());
        old.setGuardHappening(safeguardRecord.getGuardHappening());
        old.setGuardHappeningDesc(safeguardRecord.getGuardHappeningDesc());
        old.setLifeHappening(safeguardRecord.getLifeHappening());
        old.setLifeHappeningDesc(safeguardRecord.getLifeHappeningDesc());
        old.setMedicalHappening(safeguardRecord.getMedicalHappening());
        old.setMedicalHappeningDesc(safeguardRecord.getMedicalHappeningDesc());
        //old.setRuleCycle();
        //old.setTownStatus();
        old.setUpdateTime(new Date());
        //old.setVillageStatus();
        old.setWelfareHappening(safeguardRecord.getWelfareHappening());
        old.setWelfareHappeningDesc(safeguardRecord.getWelfareHappeningDesc());

        safeguardRecordService.updateSafeguardRecord(old);
        return result;
    }

    @ApiOperation(value = "保障评估记录详情", notes = "保障评估记录详情")
    @ApiImplicitParam(paramType = "query", name = "safeguardId", value = "保障评估记录id", required = true, dataType = "int")
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public BaseResult detail(@RequestParam int safeguardId) {
        BaseResult result = new BaseResult();
        SafeguardRecord safeguardRecord = safeguardRecordService.getSafeguardRecord(safeguardId);
        ChildRoster childRoster = childApplyService.getRosterInfoByChildId(safeguardRecord.getChildId());
        safeguardRecord.setChildRoster(childRoster);
        result.setData(safeguardRecord);
        return result;
    }
}
