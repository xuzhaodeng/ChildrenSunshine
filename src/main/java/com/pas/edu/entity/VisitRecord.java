package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class VisitRecord {
    @ApiModelProperty(value = "回访id")
    private int visitId;
    @ApiModelProperty(value = "孩子id")
    private int childId;
    @ApiModelProperty(value = "回访日期")
    private Date visitDate;
    @ApiModelProperty(value = "回访机构级别（3：乡镇；2：区县）")
    private int visitOrgLevel;
    @ApiModelProperty(value = "回访机构id")
    private int visitOrgId;
    @ApiModelProperty(value = "回访结果（1：正常 2：异常）")
    private int visitResult;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "操作用户ID")
    private int operateId;
    @ApiModelProperty(value = "操作用户姓名")
    private String operatorName;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
