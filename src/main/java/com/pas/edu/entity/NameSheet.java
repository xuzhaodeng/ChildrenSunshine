package com.pas.edu.entity;

/**
 * Created by wutaisong on 2017/6/3.
 */
public class NameSheet {

	private int child_id;
	private String child_name;
	private String child_sex;
	private String child_birthday;
	private String guardian_phone; // 监护人手机号
	private String child_address;

	private String plight_category; // 困境类别
	private String otherDilCatDesc; // 困境类别--其他

	private String basic_life;// 生活保障
	private String otherBasicLife; // 其他基本生活情况

	private String education_happening; // 教育情况
	private String otherEducation; // 其他教育描述

	private String medical_happening; // 医疗情况
	private String otherMedical; // 其他医疗情况

	private String guardian_hapening;// 监护情况

	private String welfare;
	private String otherWelfare;

	@Override
	public String toString() {
		return "NameSheet{" + "child_id=" + child_id + ", child_name='" + child_name + '\'' + ", child_sex='"
				+ child_sex + '\'' + ", child_birthday='" + child_birthday + '\'' + ", guardian_phone='"
				+ guardian_phone + '\'' + ", child_address='" + child_address + '\'' + ", plight_category='"
				+ plight_category + '\'' + ", otherDilCatDesc='" + otherDilCatDesc + '\'' + ", basic_life='"
				+ basic_life + '\'' + ", otherBasicLife='" + otherBasicLife + '\'' + ", education_happening='"
				+ education_happening + '\'' + ", otherEducation='" + otherEducation + '\'' + ", medical_happening='"
				+ medical_happening + '\'' + ", otherMedical='" + otherMedical + '\'' + ", guardian_hapening='"
				+ guardian_hapening + '\'' + ", welfare='" + welfare + '\'' + ", otherWelfare='" + otherWelfare + '\''
				+ '}';
	}

	public String getOtherWelfare() {
		return otherWelfare;
	}

	public void setOtherWelfare(String otherWelfare) {
		this.otherWelfare = otherWelfare;
	}

	public String getOtherDilCatDesc() {
		return otherDilCatDesc;
	}

	public void setOtherDilCatDesc(String otherDilCatDesc) {
		this.otherDilCatDesc = otherDilCatDesc;
	}

	public String getOtherBasicLife() {
		return otherBasicLife;
	}

	public void setOtherBasicLife(String otherBasicLife) {
		this.otherBasicLife = otherBasicLife;
	}

	public String getOtherEducation() {
		return otherEducation;
	}

	public void setOtherEducation(String otherEducation) {
		this.otherEducation = otherEducation;
	}

	public String getOtherMedical() {
		return otherMedical;
	}

	public void setOtherMedical(String otherMedical) {
		this.otherMedical = otherMedical;
	}

	public int getChild_id() {
		return child_id;
	}

	public void setChild_id(int child_id) {
		this.child_id = child_id;
	}

	public String getChild_name() {
		return child_name;
	}

	public void setChild_name(String child_name) {
		this.child_name = child_name;
	}

	public String getChild_sex() {
		return child_sex;
	}

	public void setChild_sex(String child_sex) {
		this.child_sex = child_sex;
	}

	public String getChild_birthday() {
		return child_birthday;
	}

	public void setChild_birthday(String child_birthday) {
		this.child_birthday = child_birthday;
	}

	public String getGuardian_phone() {
		return guardian_phone;
	}

	public void setGuardian_phone(String guardian_phone) {
		this.guardian_phone = guardian_phone;
	}

	public String getChild_address() {
		return child_address;
	}

	public void setChild_address(String child_address) {
		this.child_address = child_address;
	}

	public String getPlight_category() {
		return plight_category;
	}

	public void setPlight_category(String plight_category) {
		this.plight_category = plight_category;
	}

	public String getBasic_life() {
		return basic_life;
	}

	public void setBasic_life(String basic_life) {
		this.basic_life = basic_life;
	}

	public String getEducation_happening() {
		return education_happening;
	}

	public void setEducation_happening(String education_happening) {
		this.education_happening = education_happening;
	}

	public String getMedical_happening() {
		return medical_happening;
	}

	public void setMedical_happening(String medical_happening) {
		this.medical_happening = medical_happening;
	}

	public String getGuardian_hapening() {
		return guardian_hapening;
	}

	public void setGuardian_hapening(String guardian_hapening) {
		this.guardian_hapening = guardian_hapening;
	}

	public String getWelfare() {
		return welfare;
	}

	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
}
