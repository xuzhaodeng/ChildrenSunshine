package com.pas.edu.entity;

import lombok.Data;

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
    private int orgId;
    private int orgLevel;
    private int parentOrgId;
    private String orgName;
}
