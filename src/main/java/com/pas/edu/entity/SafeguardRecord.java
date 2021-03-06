package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SafeguardRecord {
    @ApiModelProperty(value = "Id")
    private int safeguardId;
    @ApiModelProperty(value = "孩子ID")
    private int childId;
    @ApiModelProperty(value = "规则周期")
    private String ruleCycle;
    @ApiModelProperty(value = "创建用户ID")
    private int creatorId;
    @ApiModelProperty(value = "村状态 1、未提交 2、已提交")
    private int villageStatus;
    @ApiModelProperty(value = "镇核查状态 1已核查 2、未核查")
    private int townStatus;
    @ApiModelProperty(value = "县核查状态  1已核查 2、未核查")
    private int countyStatus;
    @ApiModelProperty(value = "市核查状态  1已核查 2、未核查")
    private int cityStatus;
    @ApiModelProperty(value = "保障状态1、正常 2、停保")
    private int guaranteeStatus;
    @ApiModelProperty(value = "停保说明")
    private String guaranteeStatusDesc;
    @ApiModelProperty(value = "落实监护责任 1、已落实 2、未落实")
    private int guardHappening;
    @ApiModelProperty(value = "落实监护责任描述")
    private String guardHappeningDesc;
    @ApiModelProperty(value = "残疾儿童福利 1、已落实 2、未落实")
    private int welfareHappening;
    @ApiModelProperty(value = "残疾儿童福利描述")
    private String welfareHappeningDesc;
    @ApiModelProperty(value = "基本生活保障 1、已落实 2、未落实")
    private int lifeHappening;
    @ApiModelProperty(value = "生活情况未落实描述")
    private String lifeHappeningDesc;
    @ApiModelProperty(value = "教育保障 1、已落实 2、未落实")
    private int educationHappening;
    @ApiModelProperty(value = "教育保障描述")
    private String educationHappeningDesc;
    @ApiModelProperty(value = "基本医疗保障 1、已落实 2、未落实")
    private int medicalHappening;
    @ApiModelProperty(value = "基本医疗保障描述")
    private String medicalHappeningDesc;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "保障记录描述")
    private String description;
    @ApiModelProperty(value = "孩子信息")
    private ChildRoster childRoster;
}
