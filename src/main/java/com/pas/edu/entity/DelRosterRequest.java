package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DelRosterRequest {
	
	@ApiModelProperty("操作用户ID")
	private Integer uid;
	
	@ApiModelProperty("儿童ID，多个Id使用逗号分隔")
	private String childIds;

}
