package com.pas.edu.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.pas.edu.dao.OrganDao;
import com.pas.edu.dao.UserDao;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.User;
import com.pas.edu.entity.common.AuditStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.entity.ChildRoster;
import com.pas.edu.service.ChildApplyService;
import com.pas.edu.utils.CommUtil;

@Service
public class ChildApplyServiceImap implements ChildApplyService {
	
	@Autowired
	ChildApplyDao cpDao;
	@Autowired
	OrganDao organDao;
	@Autowired
	UserDao userDao;

	@Value("${imagePath}")
	private String imagePath;

	@Override
	public Integer addChildRoster(ChildRoster childRoster) {
		try {
			childRoster.setCreateTime(CommUtil.getDateFormat(CommUtil.getDateFormat()));
			childRoster.setUpdateTime(CommUtil.getDateFormat(CommUtil.getDateFormat()));
			Integer rows = cpDao.insertChildApply(childRoster);
			return childRoster.getChildId();
		} catch (ParseException e) {
			return 0;
		}
		
	}

	@Override
	public ChildRoster getRosterInfoByChildId(int childId) {
		return cpDao.getRosterInfoByChildId(childId);
	}

	@Override
	public void updateChildApply(ChildRoster childRoster){
		try {
			childRoster.setUpdateTime(CommUtil.getDateFormat(CommUtil.getDateFormat()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpDao.updateChildApply(childRoster);
	}

	@Override
	public List<ChildRoster> getChildApplyLsts(Integer uid, Integer currPage, Integer pageSize) {
		Page<ChildRoster> page = PageHelper.startPage(currPage * pageSize, pageSize);
		List<ChildRoster> resultLsts = cpDao.getChildApplyLsts(uid);
		return resultLsts;
	}
	
	@Override
	public List<ChildRoster> getChildApplyLstsByOrgId(Integer orgId, Integer loginUserId, Integer currPage, Integer pageSize) {
		User user = userDao.getUserById(loginUserId);
		Organ organ = organDao.getOrgan(user.getOrgId());
		Page<ChildRoster> page = PageHelper.startPage(currPage * pageSize, pageSize);

		List<ChildRoster> resultLsts = cpDao.getChildApplyLstsByOrgid(orgId,organ.getOrgLevel());
		return resultLsts;
	}

	@Override
	public void delRoster(Integer uid, String childIds) {
		if(childIds != null){
			String [] childIdsArr =  childIds.split(",");
			if(childIdsArr != null && childIdsArr.length > 0){
				for (String childid : childIdsArr) {
					cpDao.delRoster(uid, Integer.parseInt(childid));
				}
			}
		}
	}

	@Override
	public Object getRosterByChildIdCard(String idCard) {
		return cpDao.getRosterByChildIdCard(idCard);
	}

	@Override
	/**
	 * 修饰childRoster，添加状态值，监控情况、困境情况、福利情况、基本生活情况、教育情况、医疗情况
	 * @param childRoster
	 */
	public void decorateChildRoster(ChildRoster childRoster, Map<String,String> jhqkMap, Map<String,String> kjlbMap, Map<String,String> jbshqkMap, Map<String,String> jyqkMap, Map<String,String> ylqkMap, Map<String,String> flqkMap) {
		childRoster.setHeadImgPath(imagePath);
		String headImg = childRoster.getHeadImg();
		headImg = StringUtils.isBlank(headImg)?"default_head.png":headImg;
		childRoster.setHeadImg(headImg);


		//单选
		childRoster.setDilemmaCategoryTitle(kjlbMap.get(childRoster.getDilemmaCategory()));
		childRoster.setEducationHappeningTitle(jyqkMap.get(childRoster.getEducationHappening()));
		childRoster.setGuaHappeningTitle(jhqkMap.get(childRoster.getGuaHappening()));
		childRoster.setVillageStatusTitle(AuditStatus.getStatusTitle(childRoster.getVillageStatus()));

		//多选
		String basicLifeHappening = childRoster.getBasicLifeHappening();
		childRoster.setBasicLifeHappeningTitle(getTitleByCode(jbshqkMap, basicLifeHappening));

		String welfareHappening = childRoster.getWelfareHappening();
		childRoster.setWelfareHappeningTitle(getTitleByCode(flqkMap, welfareHappening));

		String medicalHappening = childRoster.getMedicalHappening();
		childRoster.setMedicalHappeningTitle(getTitleByCode(ylqkMap, medicalHappening));

		//审核状态
		childRoster.setTownStatusTitle(AuditStatus.getStatusTitle(childRoster.getTownStatus()));
		childRoster.setCountyStatusTitle(AuditStatus.getStatusTitle(childRoster.getCountyStatus()));
		childRoster.setCityStatusTitle(AuditStatus.getStatusTitle(childRoster.getCityStatus()));
	}

	private String getTitleByCode(Map<String, String> jbshqkMap, String code) {
		if(StringUtils.isNotBlank(code)) {
			String value = null;
			String[] items = code.split(",");
			for(String aaa:items) {
				if(value==null) {
					value = jbshqkMap.get(aaa);
				}
				else {
					value = value + "," + jbshqkMap.get(aaa);
				}
			}
			return value;
		}
		else {
			return null;
		}
	}
}
