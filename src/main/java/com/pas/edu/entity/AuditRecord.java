package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AuditRecord {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("申请ID")
    private Integer applyId;
    @ApiModelProperty("类型: 1、同意 2、驳回")
    private Integer type;
    @ApiModelProperty("异常内容 json格式保存")
    private String deviantContent;
    @ApiModelProperty("操作用户ID")
    private Integer operateId;
    @ApiModelProperty("操作用户姓名")
    private String operatorName;
    @ApiModelProperty("当前审核机构id")
    private Integer currentOrgId;
    @ApiModelProperty("上级机构ID")
    private Integer appointOrgId;
    @ApiModelProperty("审批记录创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("描述")
    private String description;
}
