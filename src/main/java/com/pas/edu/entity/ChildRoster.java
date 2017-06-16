package com.pas.edu.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChildRoster {
	
	@ApiModelProperty("孩子ID,数据采集添加时填空")
	private Integer childId;
	   
	@ApiModelProperty("用户图片路径")
	private String headImgPath; //用户图片路径

	@ApiModelProperty("用户图片文件名")
	private String headImg; //用户图片文件名

	@NotEmpty(message = "孩子姓名不能为空")
	@ApiModelProperty("孩子名称")
	private String childName; //孩子名称
	
	@NotEmpty(message = "孩子身份证号码不能为空")
	@ApiModelProperty("孩子身份证号码")
	private String childIdCard; // 孩子身份证号码
	
	@ApiModelProperty("孩子性别")
	private String childSex; // 孩子性别
	
	@ApiModelProperty("孩子所在地址")
	private String address; //地址
	
	@ApiModelProperty("孩子出生日期")
	private String childBornTime; //出生时间
	
	@ApiModelProperty("孩子民族")
	private String nation; //民族
	
	@ApiModelProperty("孩子家庭人数")
	private Integer familyPopulation; //家庭人数
	
	@ApiModelProperty("儿童本身原因")
	private String itselfReason; //儿童本身原因
	
	@ApiModelProperty("家庭原因")
	private String familyReason; //家庭原因
	
	@ApiModelProperty("监护原因")
	private String guaReason; //监护原因
	
	@ApiModelProperty("监护人姓名")
	private String guaName; //监护人姓名
	
	@ApiModelProperty("监护人电话号码")
	private String guaTelNum; //监护人电话号码
	
	@ApiModelProperty("监护人身份证号码")
	private String guaIdCard; //监护人身份证号码
	
	@ApiModelProperty("监护人性别")
	private String guaSex; //监护人性别
	
	@ApiModelProperty("监护人出生日期")
	private String guaBornTime; //监护人出生日期
	
	@ApiModelProperty("监护人与孩子关系")
	private String guaChildRela; //与孩子关系
	
	@ApiModelProperty("监护情况代码")
	private String guaHappening; //监护情况
	
	@ApiModelProperty("监护情况选择0描述信息")
	private String guaHappeningDesc; //监护情况

	@ApiModelProperty("监护情况名称")
	private String guaHappeningTitle; //监护情况
	
	@ApiModelProperty("儿童困境类别代码")
	private String dilemmaCategory; //困境类别

	@ApiModelProperty("儿童困境类别名称")
	private String dilemmaCategoryTitle; //困境类别

	@ApiModelProperty("困境类别选择0时补充信息")
	private String otherDilCatDesc; //困境类别--其他
	
	@ApiModelProperty("福利情况code")
	private String welfareHappening; //福利情况

	@ApiModelProperty("福利情况名称")
	private String welfareHappeningTitle; //福利情况

	@ApiModelProperty("选择未服务/未保障时补充信息")
	private String otherWelfare; //其他福利描述
	
	@ApiModelProperty("基本生活情况代码")
	private String basicLifeHappening; //基本生活情况

	@ApiModelProperty("基本生活情况名称")
	private String basicLifeHappeningTitle; //基本生活情况

	@ApiModelProperty("选择未服务/未保障时补充信息")
	private String otherBasicLife; //其他基本生活情况
	
	@ApiModelProperty("教育情况代码")
	private String  educationHappening; //教育情况

	@ApiModelProperty("教育情况名称")
	private String  educationHappeningTitle; //教育情况

	@ApiModelProperty("选择未服务/未保障时补充信息")
	private String otherEducation; //其他教育描述
	
	@ApiModelProperty("医疗情况代码")
	private String medicalHappening; //医疗情况

	@ApiModelProperty("医疗情况名称")
	private String medicalHappeningTitle; //医疗情况

	@ApiModelProperty("选择未服务/未保障时补充信息")
	private  String otherMedical; //其他医疗情况
	
	@ApiModelProperty("村审核状态")
	private Integer villageStatus; //村审核状态

	@ApiModelProperty("村审核状态名称")
	private String villageStatusTitle; //村审核状态

	@ApiModelProperty("镇审核状态")
	private Integer townStatus; //镇审核状态

	@ApiModelProperty("镇审核状态")
	private String townStatusTitle; //镇审核状态

	@ApiModelProperty("县审核状态")
	private Integer countyStatus; //县审核状态

	@ApiModelProperty("县审核状态")
	private String countyStatusTitle; //县审核状态

	@ApiModelProperty("市审核状态")
	private Integer cityStatus; //市审核状态

	@ApiModelProperty("市审核状态")
	private String cityStatusTitle; //市审核状态

	@ApiModelProperty("村管用户ID")
	private Integer operateId; //操作用户ID
	
	@ApiModelProperty("创建时间")
	private Date createTime; //创建时间
	
	@ApiModelProperty("更新时间")
	private Date updateTime; //更新时间
	
	@ApiModelProperty("省份Id")
	private Integer provinceId; //省份Id
	
	@ApiModelProperty("省份名称")
	private String provinceName; //省份名称
	
	@ApiModelProperty("市ID")
	private Integer cityId; //市id
	
	@ApiModelProperty("市名称")
	private String cityName; //市名称
	
	@ApiModelProperty("县ID")
	private Integer countyId; //县ID
	
	@ApiModelProperty("县名称")
	private String countyName; //县名称
	
	@ApiModelProperty("镇ID")
	private Integer townId; //镇id
	
	@ApiModelProperty("镇名称")
	private String townName; //镇名称
	
	@ApiModelProperty("村ID")
	private Integer villageId; //村ID
	
	@ApiModelProperty("村名称")
	private String villageName; //村名称

	@ApiModelProperty("审核记录")
	private List<AuditRecord> auditRecords;
}
