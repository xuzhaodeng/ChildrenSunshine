package com.pas.edu.entity;

public class PoiChildRoster {
	
	private String writeBy; //填写人
	
	private Integer childId;
	
	private String headImg; //用户图片
	
	private String childName; //孩子名称
	
	private String childIdCard; // 孩子身份证号码
	
	private String childSex; // 孩子性别
	
	private String address; //地址
	
	private String childBornTime; //出生时间
	
	private String nation; //民族
	
	private Integer familyPopulation; //家庭人数
	
	private String itselfReason; //儿童本身原因
	
	private String familyReason; //家庭原因
	
	private String guaReason; //监护原因
	
	private String guaName; //监护人姓名
	
	private String guaTelNum; //监护人电话号码
	
	private String guaIdCard; //监护人身份证号码
	
	private String guaSex; //监护人性别
	
	private String guaBornTime; //监护人出生日期
	
	private String guaChildRela; //与孩子关系
	
	private String guaHappening; //监护情况
	
	private String dilemmaCategory; //困境类别
	
	private String otherDilCatDesc; //困境类别--其他
	
	private String welfareHappening; //福利情况
	
	private String otherWelfare; //其他福利描述
	
	private String basicLifeHappening; //基本生活情况
	
	private String otherBasicLife; //其他基本生活情况
	
	private String  educationHappening; //教育情况
	
	private String otherEducation; //其他教育描述
	
	private String medicalHappening; //医疗情况
	
	private  String otherMedical; //其他医疗情况
	
	private Integer villageStatus; //村审核状态
	
	private Integer townStatus; //镇审核状态
	
	private Integer countyStatus; //县审核状态
	
	private Integer cityStatus; //市审核状态
	
	private Integer operateId; //操作用户ID
	
	private String createTime; //创建时间
	
	private String updateTime; //更新时间
	
	private Integer provinceId; //省份Id
	
	private String provinceName; //省份名称
	
	private Integer cityId; //市id
	
	private String cityName; //市名称
	
	private Integer countyId; //县ID
	
	private String countyName; //县名称
	
	private Integer townId; //镇id
	
	private String townName; //镇名称
	
	private Integer villageId; //村ID
	
	private String villageName; //村名称

	@Override
	public String toString() {
		return "ChildRoster{" +
				"writeBy='" + writeBy + '\'' +
				", headImg='" + headImg + '\'' +
				", childName='" + childName + '\'' +
				", childIdCard='" + childIdCard + '\'' +
				", childSex='" + childSex + '\'' +
				", address='" + address + '\'' +
				", childBornTime='" + childBornTime + '\'' +
				", nation='" + nation + '\'' +
				", familyPopulation=" + familyPopulation +
				", itselfReason='" + itselfReason + '\'' +
				", familyReason='" + familyReason + '\'' +
				", guaReason='" + guaReason + '\'' +
				", guaName='" + guaName + '\'' +
				", guaTelNum='" + guaTelNum + '\'' +
				", guaIdCard='" + guaIdCard + '\'' +
				", guaSex='" + guaSex + '\'' +
				", guaBornTime='" + guaBornTime + '\'' +
				", guaChildRela='" + guaChildRela + '\'' +
				", guaHappening='" + guaHappening + '\'' +
				", dilemmaCategory='" + dilemmaCategory + '\'' +
				", otherDilCatDesc='" + otherDilCatDesc + '\'' +
				", welfareHappening='" + welfareHappening + '\'' +
				", otherWelfare='" + otherWelfare + '\'' +
				", basicLifeHappening='" + basicLifeHappening + '\'' +
				", otherBasicLife='" + otherBasicLife + '\'' +
				", educationHappening='" + educationHappening + '\'' +
				", otherEducation='" + otherEducation + '\'' +
				", medicalHappening='" + medicalHappening + '\'' +
				", otherMedical='" + otherMedical + '\'' +
				", villageStatus=" + villageStatus +
				", townStatus=" + townStatus +
				", countyStatus=" + countyStatus +
				", cityStatus=" + cityStatus +
				", operateId=" + operateId +
				", createTime='" + createTime + '\'' +
				", updateTime='" + updateTime + '\'' +
				", provinceId=" + provinceId +
				", provinceName='" + provinceName + '\'' +
				", cityId=" + cityId +
				", cityName='" + cityName + '\'' +
				", countyId=" + countyId +
				", countyName='" + countyName + '\'' +
				", townId=" + townId +
				", townName='" + townName + '\'' +
				", villageId=" + villageId +
				", villageName='" + villageName + '\'' +
				'}';
	}

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public String getWriteBy() {
		return writeBy;
	}

	public void setWriteBy(String writeBy) {
		this.writeBy = writeBy;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildIdCard() {
		return childIdCard;
	}

	public void setChildIdCard(String childIdCard) {
		this.childIdCard = childIdCard;
	}

	public String getChildSex() {
		return childSex;
	}

	public void setChildSex(String childSex) {
		this.childSex = childSex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getChildBornTime() {
		return childBornTime;
	}

	public void setChildBornTime(String childBornTime) {
		this.childBornTime = childBornTime;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Integer getFamilyPopulation() {
		return familyPopulation;
	}

	public void setFamilyPopulation(Integer familyPopulation) {
		this.familyPopulation = familyPopulation;
	}

	public String getItselfReason() {
		return itselfReason;
	}

	public void setItselfReason(String itselfReason) {
		this.itselfReason = itselfReason;
	}

	public String getFamilyReason() {
		return familyReason;
	}

	public void setFamilyReason(String familyReason) {
		this.familyReason = familyReason;
	}

	public String getGuaReason() {
		return guaReason;
	}

	public void setGuaReason(String guaReason) {
		this.guaReason = guaReason;
	}

	public String getGuaName() {
		return guaName;
	}

	public void setGuaName(String guaName) {
		this.guaName = guaName;
	}

	public String getGuaTelNum() {
		return guaTelNum;
	}

	public void setGuaTelNum(String guaTelNum) {
		this.guaTelNum = guaTelNum;
	}

	public String getGuaIdCard() {
		return guaIdCard;
	}

	public void setGuaIdCard(String guaIdCard) {
		this.guaIdCard = guaIdCard;
	}

	public String getGuaSex() {
		return guaSex;
	}

	public void setGuaSex(String guaSex) {
		this.guaSex = guaSex;
	}

	public String getGuaBornTime() {
		return guaBornTime;
	}

	public void setGuaBornTime(String guaBornTime) {
		this.guaBornTime = guaBornTime;
	}

	public String getGuaChildRela() {
		return guaChildRela;
	}

	public void setGuaChildRela(String guaChildRela) {
		this.guaChildRela = guaChildRela;
	}

	public String getGuaHappening() {
		return guaHappening;
	}

	public void setGuaHappening(String guaHappening) {
		this.guaHappening = guaHappening;
	}

	public String getDilemmaCategory() {
		return dilemmaCategory;
	}

	public void setDilemmaCategory(String dilemmaCategory) {
		this.dilemmaCategory = dilemmaCategory;
	}

	public String getOtherDilCatDesc() {
		return otherDilCatDesc;
	}

	public void setOtherDilCatDesc(String otherDilCatDesc) {
		this.otherDilCatDesc = otherDilCatDesc;
	}

	public String getWelfareHappening() {
		return welfareHappening;
	}

	public void setWelfareHappening(String welfareHappening) {
		this.welfareHappening = welfareHappening;
	}

	public String getOtherWelfare() {
		return otherWelfare;
	}

	public void setOtherWelfare(String otherWelfare) {
		this.otherWelfare = otherWelfare;
	}

	public String getBasicLifeHappening() {
		return basicLifeHappening;
	}

	public void setBasicLifeHappening(String basicLifeHappening) {
		this.basicLifeHappening = basicLifeHappening;
	}

	public String getOtherBasicLife() {
		return otherBasicLife;
	}

	public void setOtherBasicLife(String otherBasicLife) {
		this.otherBasicLife = otherBasicLife;
	}

	public String getEducationHappening() {
		return educationHappening;
	}

	public void setEducationHappening(String educationHappening) {
		this.educationHappening = educationHappening;
	}

	public String getOtherEducation() {
		return otherEducation;
	}

	public void setOtherEducation(String otherEducation) {
		this.otherEducation = otherEducation;
	}

	public String getMedicalHappening() {
		return medicalHappening;
	}

	public void setMedicalHappening(String medicalHappening) {
		this.medicalHappening = medicalHappening;
	}

	public String getOtherMedical() {
		return otherMedical;
	}

	public void setOtherMedical(String otherMedical) {
		this.otherMedical = otherMedical;
	}

	public Integer getVillageStatus() {
		return villageStatus;
	}

	public void setVillageStatus(Integer villageStatus) {
		this.villageStatus = villageStatus;
	}

	public Integer getTownStatus() {
		return townStatus;
	}

	public void setTownStatus(Integer townStatus) {
		this.townStatus = townStatus;
	}

	public Integer getCountyStatus() {
		return countyStatus;
	}

	public void setCountyStatus(Integer countyStatus) {
		this.countyStatus = countyStatus;
	}

	public Integer getCityStatus() {
		return cityStatus;
	}

	public void setCityStatus(Integer cityStatus) {
		this.cityStatus = cityStatus;
	}

	public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

}
