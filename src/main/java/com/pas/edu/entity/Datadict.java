package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据字典
 */
@Data
public class Datadict {
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("类别")
    private String type;
    @ApiModelProperty("代码")
    private String code;
    @ApiModelProperty("拼音首字母编码")
    private String pinyinCode;
    @ApiModelProperty("名称")
    private String title;
}
