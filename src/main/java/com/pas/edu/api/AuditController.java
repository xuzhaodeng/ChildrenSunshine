package com.pas.edu.api;

import com.pas.edu.entity.AuditRecord;
import com.pas.edu.entity.AuditRequest;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.AuditRecordService;
import com.pas.edu.service.ChildApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "审核", tags = "审核接口")
@RestController
@RequestMapping("api/audit")
public class AuditController extends BaseController {

    @Autowired
    private AuditRecordService auditRecordService;

    @Autowired
    ChildApplyService childApplyService;

    @ApiOperation(value = "审核操作", notes = "对儿童登记表进行审核操作 <br/>action取值(PASS：通过；REFUSE：驳回)  <br/>level取值 (0：村；1：乡镇；2：区县；3：市)  <br/>wrongFields为错误字段，多个错误字段用逗号分隔")
    @RequestMapping(value = "action", method = RequestMethod.POST)
    public Result audit(@RequestBody AuditRequest auditRequest) {
        System.out.println(auditRequest);
        //儿童等级表存在村、镇、县、市的状态
        //每级都有（1、采集中 2、已提交/待审核 3、已驳回 4、已通过）
        //所有级别初始状态都为5

        ChildRoster childRoster = null;
        //ChildRoster childRoster = childApplyService.getChildApply();
        if(childRoster==null) {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("找不到对应的儿童登记表");
            return result;
        }

        //村提交
        if(auditRequest.getLevel()==0) {
            //村状态置2，镇状态置2
            childRoster.setVillageStatus(2);
            childRoster.setTownStatus(2);
        }
        //乡镇审核
        else if(auditRequest.getLevel()==1) {
            //审核通过:村状态置2，镇状态置4
            //审核拒绝:村状态置3，镇状态置3
            if("PASS".equals(auditRequest.getAction())) {
                childRoster.setVillageStatus(2);
                childRoster.setTownStatus(4);
            }
            else {
                childRoster.setVillageStatus(3);
                childRoster.setTownStatus(3);
            }
        }
        //区县审核
        else if(auditRequest.getLevel()==2) {
            //审核通过：村状态置2，镇状态置4，县状态置4
            //审核拒绝：村状态置3，镇状态置3，县状态置3
            if("PASS".equals(auditRequest.getAction())) {
                childRoster.setVillageStatus(2);
                childRoster.setTownStatus(4);
                childRoster.setCountyStatus(4);
            }
            else {
                childRoster.setVillageStatus(3);
                childRoster.setTownStatus(3);
                childRoster.setCountyStatus(3);
            }
        }
        //市审核
        else if(auditRequest.getLevel()==3) {
            //审核通过：村状态置2，镇状态置4，县状态置4，市状态置4
            //审核拒绝：村状态置3，镇状态置3，县状态置3，市状态置3
            if("PASS".equals(auditRequest.getAction())) {
                childRoster.setVillageStatus(2);
                childRoster.setTownStatus(4);
                childRoster.setCountyStatus(4);
                childRoster.setCityStatus(4);
            }
            else {
                childRoster.setVillageStatus(3);
                childRoster.setTownStatus(3);
                childRoster.setCountyStatus(3);
                childRoster.setCityStatus(3);
            }
        }


        //生成审核记录
        AuditRecord auditRecord = new AuditRecord();
        //auditRecord.setApplyId();
        auditRecordService.createAuditRecord(auditRecord);

        return new Result();
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public Result test() {
        return new Result();
    }
}
