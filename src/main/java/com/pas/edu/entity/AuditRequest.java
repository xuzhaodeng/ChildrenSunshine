package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class AuditRequest {

    /**
     * 申请的id
     */
    @NotEmpty
    @ApiModelProperty("申请的id")
    private int applyId;
    /**
     * 级别(4:村；3：乡镇；2：区县；1：市)
     */
    @NotEmpty
    @ApiModelProperty("级别(4:村；3：乡镇；2：区县；1：市)")
    private int level;

    /**
     *操作(1：通过；2：驳回)
     */
    @NotEmpty
    @ApiModelProperty("操作(1：通过；2：驳回)")
    private int action;

    /**
     * 错误字段，多个错误字段用逗号分隔
     */
    //@ApiModelProperty("")
    //private String wrongFields;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 操作人员id
     */
    @ApiModelProperty("操作人员id")
    @NotEmpty
    private int operatorId;

    @Override
    public String toString() {
        return "AuditRequest{" +
                "applyId=" + applyId +
                ", level=" + level +
                ", action='" + action + '\'' +
                //", wrongFields='" + wrongFields + '\'' +
                ", remark='" + remark + '\'' +
                ", operatorId=" + operatorId +
                '}';
    }
}
