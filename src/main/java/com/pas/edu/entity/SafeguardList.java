package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SafeguardList {
	
	@ApiModelProperty("保障评估记录ID")
	private Integer safeguardId;
	
	@ApiModelProperty("孩子ID")
	private Integer childId;
	
	@ApiModelProperty("孩子姓名")
	private String childName;
	
	@ApiModelProperty("保障评估个数")
	private Integer safeguardNum;
	
	@ApiModelProperty("已落实个数")
	private Integer alreadySafeguardNum;
	
	@ApiModelProperty("当前状态 1、已保障 2、未保障")
	private Integer status;

}
