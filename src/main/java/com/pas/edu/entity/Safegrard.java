package com.pas.edu.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Safegrard {
	@ApiModelProperty(value = "保障评估记录ID")
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
    
    @ApiModelProperty(value = "监护情况是否落实 1、已落实 2、为落实")
    private int guardHappening;
    
    @ApiModelProperty(value = "监护情况未落实描述")
    private String guardHappeningDesc;
    
    @ApiModelProperty(value = "困境儿童福利情况是否落实 1、已落实 2、未落实")
    private int welfareHappening;
    
    @ApiModelProperty(value = "福利情况未落实描述")
    private String welfareHappeningDesc;
    
    @ApiModelProperty(value = "生活情况是否落实 1、已落实 2、未落实")
    private int lifeHappening;
    
    @ApiModelProperty(value = "生活情况未落实描述")
    private String lifeHappeningDesc;
    
    @ApiModelProperty(value = "教育情况是否落实 1、已落实 2、未落实")
    private int educationHappening;
    
    @ApiModelProperty(value = "教育情况未落实描述")
    private String educationHappeningDesc;
    
    @ApiModelProperty(value = "医疗情况是否落实 1、已落实 2、未落实")
    private int medicalHappening;
    
    @ApiModelProperty(value = "医疗情况未落实描述")
    private String medicalHappeningDesc;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
    @ApiModelProperty(value = "保障记录描述")
    private String description;
    
    @ApiModelProperty("省份Id")
	private Integer provinceId; //省份Id
	
	@ApiModelProperty("省份名称")
	private String provinceName; //省份名称
	
	@ApiModelProperty("市ID")
	private Integer cityId; //市id
	
	@ApiModelProperty("市名称")
	private String cityName; //市名称
	
	@ApiModelProperty("县ID")
	private Integer countyId; //县ID
	
	@ApiModelProperty("县名称")
	private String countyName; //县名称
	
	@ApiModelProperty("镇ID")
	private Integer townId; //镇id
	
	@ApiModelProperty("镇名称")
	private String townName; //镇名称
	
	@ApiModelProperty("村ID")
	private Integer villageId; //村ID
	
	@ApiModelProperty("村名称")
	private String villageName; //村名称
}
