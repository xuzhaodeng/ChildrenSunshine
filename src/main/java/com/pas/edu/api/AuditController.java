package com.pas.edu.api;

import com.pas.edu.entity.AuditRecord;
import com.pas.edu.entity.AuditRequest;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.User;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.AuditRecordService;
import com.pas.edu.service.ChildApplyService;
import com.pas.edu.service.HistoryRosterService;
import com.pas.edu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(value = "审核", tags = "审核接口")
@RestController
@RequestMapping("api/audit")
public class AuditController extends BaseController {

    @Autowired
    private AuditRecordService auditRecordService;

    @Autowired
    private ChildApplyService childApplyService;

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryRosterService historyRosterService;

    @ApiOperation(value = "提交审核", notes = "村把申请材料提交到镇进行审核")
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public Result submit(@RequestParam(value = "applyId", required = true) Integer applyId) {
        Date now = new Date();

        //儿童等级表存在村、镇、县、市的状态
        //每级都有（1、采集中 2、已提交 3、审核中 4、被驳回 5、已通过）
        //所有级别初始状态都为1

        //ChildRoster childRoster = null;
        ChildRoster childRoster = childApplyService.getRosterInfoByChildId(applyId);
        if(childRoster==null) {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("找不到对应的儿童登记表");
            return result;
        }

        //村状态置2，镇状态置3
        childRoster.setVillageStatus(2);
        childRoster.setTownStatus(3);

        childRoster.setUpdateTime(now);
        childApplyService.updateChildApply(childRoster);

        return new Result();
    }

    @ApiOperation(value = "审核操作", notes = "对儿童登记表进行审核操作 <br/>action取值(1：同意；2：驳回)  <br/>level取值 (4：村；3：乡镇；2：区县；1：市)  <br/>wrongFields为错误字段，多个错误字段用逗号分隔")
    @RequestMapping(value = "action", method = RequestMethod.POST)
    public Result audit(@RequestBody AuditRequest auditRequest) {
        System.out.println(auditRequest);
        Date now = new Date();

        //儿童等级表存在村、镇、县、市的状态
        //每级都有（1、采集中 2、已提交 3、审核中 4、被驳回 5、已通过）
        //所有级别初始状态都为1

        //ChildRoster childRoster = null;
        ChildRoster childRoster = childApplyService.getRosterInfoByChildId(auditRequest.getApplyId());
        if(childRoster==null) {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("找不到对应的儿童登记表");
            return result;
        }

        //村提交审核
        if(auditRequest.getLevel()==4) {
            //村状态置2，镇状态置3
            childRoster.setVillageStatus(2);
            childRoster.setTownStatus(3);
        }
        //乡镇审核
        else if(auditRequest.getLevel()==3) {
            //审核通过:村状态置2，镇状态置2，县状态置3
            //审核拒绝:村状态置3，镇状态置3
            if(auditRequest.getAction()==1) {
                childRoster.setVillageStatus(2);
                childRoster.setTownStatus(2);
                childRoster.setCountyStatus(3);
            }
            else {
                childRoster.setVillageStatus(4);
                childRoster.setTownStatus(4);
            }
        }
        //区县审核
        else if(auditRequest.getLevel()==2) {
            //审核通过：村状态置2，镇状态置2，县状态置2，市状态置2
            //审核拒绝：村状态置4，镇状态置4，县状态置4
            if(auditRequest.getAction()==1) {
                childRoster.setVillageStatus(2);
                childRoster.setTownStatus(2);
                childRoster.setCountyStatus(2);
                childRoster.setCityStatus(3);
            }
            else {
                childRoster.setVillageStatus(4);
                childRoster.setTownStatus(4);
                childRoster.setCountyStatus(4);
            }
        }
        //市审核
        else if(auditRequest.getLevel()==1) {
            //审核通过：村状态置2，镇状态置5，县状态置5，市状态置5
            //审核拒绝：村状态置3，镇状态置3，县状态置3，市状态置3
            if(auditRequest.getAction()==1) {
                childRoster.setVillageStatus(5);
                childRoster.setTownStatus(5);
                childRoster.setCountyStatus(5);
                childRoster.setCityStatus(5);
            }
            else {
                childRoster.setVillageStatus(4);
                childRoster.setTownStatus(4);
                childRoster.setCountyStatus(4);
                childRoster.setCityStatus(4);
            }
        }

        childRoster.setUpdateTime(now);
        childApplyService.updateChildApply(childRoster);

        User user =  userService.getUser(auditRequest.getOperatorId());

        //生成审核记录
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setApplyId(auditRequest.getApplyId());
        auditRecord.setAppointOrgId(user.getOrgParentId());
        auditRecord.setCreateTime(now);
        auditRecord.setCurrentOrgId(user.getOrgId());
        auditRecord.setDescription(auditRequest.getRemark());
        auditRecord.setDeviantContent(null);
        auditRecord.setOperateId(auditRequest.getOperatorId());
        auditRecord.setType(auditRequest.getAction());
        //auditRecord.setUpdateTime(now);
        auditRecordService.createAuditRecord(auditRecord);

        //市审核通过，则在存一份数据到历史表中
        if(auditRequest.getLevel()==1 && auditRequest.getAction()==1) {
            historyRosterService.insertHistoryRoster(auditRequest.getApplyId());
        }

        return new Result();
    }

    @ApiOperation(value = "审核记录列表", notes = "列出审核记录信息")
    @RequestMapping(value = "auditRecordList", method = RequestMethod.GET)
    public Result auditRecordList(@RequestParam(value = "applyId", required = true) Integer applyId) {
        List<AuditRecord> auditRecordList = auditRecordService.getAuditRecordList(applyId);

        for(AuditRecord item:auditRecordList) {
            User user = userService.getUser(item.getOperateId());
            if(user!=null) {
                item.setOperatorName(user.getName());
            }
        }
        Result result = new Result();
        result.setData(auditRecordList);

        return result;
    }
}
