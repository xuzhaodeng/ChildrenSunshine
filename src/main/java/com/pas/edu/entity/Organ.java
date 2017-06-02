package com.pas.edu.entity;

import com.sun.org.apache.xpath.internal.operations.Or;
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
    private int orgId;
    //机构级别  1,2,3,4
    private int orgLevel;
    //父机构ID
    private int parentOrgId;
    //机构名
    private String orgName;
    //机构代号
    private String orgCode;
    //父机构
    private Organ parentOrgan;
    //子机构
    private List<Organ> childOrganList;
}
