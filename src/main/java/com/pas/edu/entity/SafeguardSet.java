package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SafeguardSet {
	@ApiModelProperty(value = "保障评估记录ID")
	private Integer sfId;
	
	@ApiModelProperty(value = "操作用户ID")
	private Integer uid;
}
