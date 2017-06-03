package com.pas.edu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AuditRecord {
    private Integer id;
    private Integer applyId;
    private Integer type;
    private String deviantContent;
    private Integer operateId;
    private Integer currentOrgId;
    private Integer appointOrgId;
    private Date createTime;
    private Date updateTime;
    private String description;
}
