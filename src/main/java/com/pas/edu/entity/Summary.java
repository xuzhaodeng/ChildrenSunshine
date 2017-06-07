package com.pas.edu.entity;


/**
 * Author: eric
 * CreateDate: 2017/6/3
 * Modified: eric
 * ModifiedDate: 2017/6/3
 * Email: ericli_wang@163.com
 * Version: 1.0
 * Desc:
 */

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

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getOrphanCount() {
        return orphanCount;
    }

    public void setOrphanCount(int orphanCount) {
        this.orphanCount = orphanCount;
    }

    public int getProvertyCount() {
        return provertyCount;
    }

    public void setProvertyCount(int provertyCount) {
        this.provertyCount = provertyCount;
    }

    public int getDisabilityCount() {
        return disabilityCount;
    }

    public void setDisabilityCount(int disabilityCount) {
        this.disabilityCount = disabilityCount;
    }

    public int getOtherDifficultCount() {
        return otherDifficultCount;
    }

    public void setOtherDifficultCount(int otherDifficultCount) {
        this.otherDifficultCount = otherDifficultCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBaseProtectCount() {
        return baseProtectCount;
    }

    public void setBaseProtectCount(int baseProtectCount) {
        this.baseProtectCount = baseProtectCount;
    }

    public int getBaseNotProtectCount() {
        return baseNotProtectCount;
    }

    public void setBaseNotProtectCount(int baseNotProtectCount) {
        this.baseNotProtectCount = baseNotProtectCount;
    }

    public int getEduProtectCount() {
        return eduProtectCount;
    }

    public void setEduProtectCount(int eduProtectCount) {
        this.eduProtectCount = eduProtectCount;
    }

    public int getEduNotProtectCount() {
        return eduNotProtectCount;
    }

    public void setEduNotProtectCount(int eduNotProtectCount) {
        this.eduNotProtectCount = eduNotProtectCount;
    }

    public int getMedicalProtectCount() {
        return medicalProtectCount;
    }

    public void setMedicalProtectCount(int medicalProtectCount) {
        this.medicalProtectCount = medicalProtectCount;
    }

    public int getMedicalNotProtectCount() {
        return medicalNotProtectCount;
    }

    public void setMedicalNotProtectCount(int medicalNotProtectCount) {
        this.medicalNotProtectCount = medicalNotProtectCount;
    }

    public int getCustodyCount() {
        return custodyCount;
    }

    public void setCustodyCount(int custodyCount) {
        this.custodyCount = custodyCount;
    }

    public int getCustodyNotCount() {
        return custodyNotCount;
    }

    public void setCustodyNotCount(int custodyNotCount) {
        this.custodyNotCount = custodyNotCount;
    }

    public int getDisabilityWelfareCount() {
        return disabilityWelfareCount;
    }

    public void setDisabilityWelfareCount(int disabilityWelfareCount) {
        this.disabilityWelfareCount = disabilityWelfareCount;
    }

    public int getDisabilityNotWelfareCount() {
        return disabilityNotWelfareCount;
    }

    public void setDisabilityNotWelfareCount(int disabilityNotWelfareCount) {
        this.disabilityNotWelfareCount = disabilityNotWelfareCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override 
    public String toString() {
        return "Summary{" +
                "orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                ", orphanCount=" + orphanCount +
                ", provertyCount=" + provertyCount +
                ", disabilityCount=" + disabilityCount +
                ", otherDifficultCount=" + otherDifficultCount +
                ", total=" + total +
                ", baseProtectCount=" + baseProtectCount +
                ", baseNotProtectCount=" + baseNotProtectCount +
                ", eduProtectCount=" + eduProtectCount +
                ", eduNotProtectCount=" + eduNotProtectCount +
                ", medicalProtectCount=" + medicalProtectCount +
                ", medicalNotProtectCount=" + medicalNotProtectCount +
                ", custodyCount=" + custodyCount +
                ", custodyNotCount=" + custodyNotCount +
                ", disabilityWelfareCount=" + disabilityWelfareCount +
                ", disabilityNotWelfareCount=" + disabilityNotWelfareCount +
                ", status=" + status +
                '}';
    }
}
