package com.pas.edu.api;

import com.pas.edu.entity.AuditRecord;
import com.pas.edu.entity.AuditRequest;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.User;
import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.AuditRecordService;
import com.pas.edu.service.ChildApplyService;
import com.pas.edu.service.HistoryRosterService;
import com.pas.edu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger logger = LoggerFactory.getLogger(AuditController.class);

    @ApiOperation(value = "提交审核", notes = "村把申请材料提交到镇进行审核")
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public Result submit(@RequestBody SubmitAudit submitAudit) {
        Date now = new Date();

        //儿童等级表存在村、镇、县、市的状态
        //每级都有（1、采集中 2、已提交 3、审核中 4、被驳回 5、已通过）
        //所有级别初始状态都为1

        //ChildRoster childRoster = null;
        ChildRoster childRoster = childApplyService.getRosterInfoByChildId(submitAudit.getApplyId());
        if(childRoster==null) {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("applyId参数不正确，找不到对应的儿童登记表");
            return result;
        }
        int villageStatus = childRoster.getVillageStatus();
        if(villageStatus!=1 && villageStatus!=4) {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("当前登记表状态不是采集中或被驳回，不允许重复提交审核");
            return result;
        }

        //村状态置2，镇状态置3
        childRoster.setVillageStatus(2);
        childRoster.setTownStatus(3);

        childRoster.setUpdateTime(now);
        childApplyService.updateChildApply(childRoster);

        return new Result();
    }

    @ApiOperation(value = "审核操作", notes = "对儿童登记表进行审核操作")
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Result audit(@RequestBody AuditRequest auditRequest) {
        logger.debug(auditRequest.toString());
        Date now = new Date();

        //儿童等级表存在村、镇、县、市的状态
        //每级都有（1、采集中 2、已提交 3、审核中 4、被驳回 5、已通过）
        //所有级别初始状态都为1

        if(auditRequest.getAction()!=1 && auditRequest.getAction()!=2) {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("action参数不正确");
            return result;
        }
        if(auditRequest.getAction()==2 && StringUtils.isBlank(auditRequest.getRemark())) {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("驳回操作时，必须填写驳回的理由");
            return result;
        }

        //ChildRoster childRoster = null;
        ChildRoster childRoster = childApplyService.getRosterInfoByChildId(auditRequest.getApplyId());
        if(childRoster==null) {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("applyId数据不正确，找不到对应的儿童登记表");
            return result;
        }
        int status = 0;
        switch (auditRequest.getLevel()) {
            case 4:
                status = childRoster.getVillageStatus();
                break;
            case 3:
                status = childRoster.getTownStatus();
                break;
            case 2:
                status = childRoster.getCountyStatus();
                break;
            case 1:
                status = childRoster.getCityStatus();
                break;
            default:
                status = 0;
        }

        if(status!=3) {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("当前申请的状态不是“审核中”，不能进行审核");
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
        else {
            Result result = new Result();
            result.setCode(-1);
            result.setMsg("level参数不正确");
            return result;
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
        auditRecord.setOperatorName(user.getName());
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
    @ApiImplicitParam(paramType = "query", name = "applyId", value = "申请id", required = true, dataType = "int")
    @RequestMapping(value = "auditRecordList", method = RequestMethod.GET)
    public BaseResult<List<AuditRecord>> auditRecordList(@RequestParam(value = "applyId", required = true) Integer applyId) {
        List<AuditRecord> auditRecordList = auditRecordService.getAuditRecordList(applyId);
        BaseResult result = new BaseResult();
        result.setData(auditRecordList);
        return result;
    }
}

@Data
class SubmitAudit{
    @NotEmpty
    @ApiModelProperty("申请的id")
    Integer applyId;
}

