package com.pas.edu.api;

import com.pas.edu.entity.AuditRecord;
import com.pas.edu.entity.AuditRequest;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.AuditRecordService;
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

    @ApiOperation(value = "审核操作", notes = "对儿童登记表进行审核操作 <br/>action取值(PASS：通过；REFUSE：驳回)  <br/>level取值 (0：乡镇；1：区县；2：市)  <br/>wrongFields为错误字段，多个错误字段用逗号分隔")
    @RequestMapping(value = "action", method = RequestMethod.POST)
    public Result audit(@RequestBody AuditRequest auditRequest) {
        System.out.println(auditRequest);
        //儿童等级表存在村、镇、县、市的状态
        //每级都有（1、采集中 2、已提交 3、已驳回 4、已通过 5、审核中）
        //所有级别初始状态都为5

        //乡镇审核
        if(auditRequest.getLevel()==0) {
            //审核通过:村状态置2，镇状态置5
            //审核拒绝:村状态置3，镇状态置3

        }
        //区县审核
        else if(auditRequest.getLevel()==1) {
            //审核通过：镇状态置5
            //审核拒绝

        }
        //市审核
        else if(auditRequest.getLevel()==2) {
            //审核通过
            //审核拒绝

        }


        //生成审核记录
        AuditRecord auditRecord = new AuditRecord();
        //auditRecord.setApplyId();
        auditRecordService.createAuditRecord(auditRecord);

        return new Result();
    }
}
