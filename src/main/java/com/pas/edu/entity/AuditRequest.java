package com.pas.edu.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class AuditRequest {

    /**
     * 申请的id
     */
    @NotEmpty
    private Integer applyId;
    /**
     * 级别(0：乡镇；1：区县；2：市)
     */
    @NotEmpty
    private Integer level;

    /**
     *操作(PASS：通过；REFUSE：驳回)
     */
    @NotEmpty
    private String action;

    /**
     * 错误字段，多个错误字段用逗号分隔
     */
    private String wrongFields;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人员id
     */
    @NotEmpty
    private Integer operatorId;

    @Override
    public String toString() {
        return "AuditRequest{" +
                "applyId=" + applyId +
                ", level=" + level +
                ", action='" + action + '\'' +
                ", wrongFields='" + wrongFields + '\'' +
                ", remark='" + remark + '\'' +
                ", operatorId=" + operatorId +
                '}';
    }
}
