package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SafeguardReport {
    private int orgId;
    private String orgName;
    @ApiModelProperty(value = "孤儿数量")
    private int orphanCount;
    @ApiModelProperty(value = "特困儿童数量")
    private int provertyCount;
    @ApiModelProperty(value = "重病伤残数量")
    private int disabilityCount;
    @ApiModelProperty(value = "其他困境儿童数量")
    private int otherDifficultCount;
    @ApiModelProperty(value = "合计数量")
    private int total;
    @ApiModelProperty(value = "基本保障已落实数量")
    private int baseProtectCount;
    private int baseNotProtectCount;
    @ApiModelProperty(value = "教育保障已落实数量")
    private int eduProtectCount;
    private int eduNotProtectCount;
    @ApiModelProperty(value = "基本医疗保障已落实数量")
    private int medicalProtectCount;
    private int medicalNotProtectCount;
    @ApiModelProperty(value = "监护责任已落实数量")
    private int custodyCount;
    private int custodyNotCount;
    @ApiModelProperty(value = "残疾儿童福利已落实数量")
    private int disabilityWelfareCount;
    private int disabilityNotWelfareCount;
//    @ApiModelProperty(value = "状态")
//    private int status;

}
