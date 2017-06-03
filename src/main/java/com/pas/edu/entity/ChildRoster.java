package com.pas.edu.entity;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class ChildRoster {
	
	private String headImg; //用户图片
	
	@NotEmpty(message = "孩子姓名不能为空")
	private String childName; //孩子名称
	
	@NotEmpty(message = "孩子身份证号码不能为空")
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
	
}
