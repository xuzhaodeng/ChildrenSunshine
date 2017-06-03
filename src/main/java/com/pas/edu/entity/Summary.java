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
public class Summary {
    //机构ID
    private int orgId;
    //机构名称
    private String orgName;
    //孤儿
    private int orphanCount;
    //特困儿童
    private int provertyCount;
    //重病伤残数量
    private int disabilityCount;
    //其他困境儿童
    private int otherDifficultCount;
    //合计
    private int total;
    //基本保障
    private int baseProtectCount;
    private int baseNotProtectCount;
    //教育保障
    private int eduProtectCount;
    private int eduNotProtectCount;
    //基本医疗保障
    private int medicalProtectCount;
    private int medicalNotProtectCount;
    //监护责任
    private int custodyCount;
    private int custodyNotCount;
    //残疾儿童福利
    private int disabilityWelfareCount;
    private int disabilityNotWelfareCount;
    //状态
    private int status;

}
