package com.pas.edu.entity;

import com.sun.org.apache.xpath.internal.operations.Or;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/6/1  17:54
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
@Data
public class Organ {
    //机构ID
    @ApiModelProperty(value = "机构id")
    private int orgId;
    //机构级别  1,2,3,4
    @ApiModelProperty(value = "机构级别 1,2,3,4")
    private int orgLevel;
    @ApiModelProperty(value = "父机构ID")
    private int parentOrgId;
    @ApiModelProperty(value = "机构名")
    private String orgName;
    @ApiModelProperty(value = "机构代号")
    private String orgCode;
    @ApiModelProperty(value = "父机构")
    private Organ parentOrgan;
    @ApiModelProperty(value = "子机构list")
    private List<Organ> childOrganList;
}
