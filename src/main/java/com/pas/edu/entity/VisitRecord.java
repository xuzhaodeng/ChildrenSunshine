package com.pas.edu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class VisitRecord {
    private int visitId;
    private int childId;
    private Date visitDate;
    private int visitOrgLevel;
    private int visitOrgId;
    private int visitResult;
    private String remark;
    private int operateId;
    private String operatorName;
    private Date createTime;

}
