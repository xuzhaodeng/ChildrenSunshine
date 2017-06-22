package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Author : eric
 * CreateDate : 2017/6/22  9:45
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  机构编辑（新增/添加）
 * Modified :
 */
@Data
public class OrganEditRequest {
    //机构ID
    @ApiModelProperty(value = "机构id",example = "1000")
    private int orgId;
    //机构级别  1,2,3,4
    @ApiModelProperty(value = "机构级别 1,2,3,4",example = "2")
    private int orgLevel;
    @ApiModelProperty(value = "父机构ID",example = "1")
    private int parentOrgId;
    @NotEmpty
    @ApiModelProperty(value = "机构名",example = "南山区")
    private String orgName;
    @NotEmpty
    @ApiModelProperty(value = "机构代号",example = "9999")
    private String orgCode;
    @ApiModelProperty(value = "操作用户id",example = "1")
    private int operateUserId;
}
