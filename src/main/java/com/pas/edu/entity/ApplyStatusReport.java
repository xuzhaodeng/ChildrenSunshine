package com.pas.edu.entity;

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
    private int notAuditCount;
    private int inAuditCount;
    private int passCount;
    private int refuseCount;
}
