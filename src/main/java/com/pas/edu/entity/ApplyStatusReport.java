package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author: eric
 * CreateDate: 2017/6/3
 * Modified: eric
 * ModifiedDate: 2017/6/3
 * Email: ericli_wang@163.com
 * Version: 1.0
 * Desc:
 */
@Data
public class ApplyStatusReport {
    private int orgId;
    @ApiModelProperty(value = "未提交/待审核数量")
    private int notAuditCount;
    @ApiModelProperty(value = "已提交/审核中数量")
    private int inAuditCount;
    @ApiModelProperty(value = "通过数量")
    private int passCount;
    @ApiModelProperty(value = "被驳回数量")
    private int refuseCount;
}
