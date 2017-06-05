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
    private Integer applyId;
    /**
     * 级别(0:村；1：乡镇；2：区县；3：市)
     */
    @NotEmpty
    @ApiModelProperty("级别(0:村；1：乡镇；2：区县；3：市)")
    private Integer level;

    /**
     *操作(PASS：通过；REFUSE：驳回)
     */
    @NotEmpty
    @ApiModelProperty("操作(1：通过；2：驳回)")
    private Integer action;

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
    private Integer operatorId;

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
