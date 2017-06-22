package com.pas.edu.service;

import java.util.List;
import java.util.Map;

import com.pas.edu.entity.ChildRoster;

public interface ChildApplyService {

	Integer addChildRoster(ChildRoster childRoster);

	ChildRoster getRosterInfoByChildId(int childId);

	void updateChildApply(ChildRoster childRoster);

	List<ChildRoster> getChildApplyLsts(Integer uid, Integer page, Integer pageSize);

	List<ChildRoster> getChildApplyLstsByOrgId(Integer orgId, Integer loginUserId, Integer currPage, Integer pageSize);


	void delRoster(Integer uid, String childIds);

	Object getRosterByChildIdCard(String idCard);

	/**
	 * 修饰childRoster，添加状态值，监控情况、困境情况、福利情况、基本生活情况、教育情况、医疗情况
	 *
	 * @param childRoster
	 */
	public void decorateChildRoster(ChildRoster childRoster, Map<String, String> jhqkMap, Map<String, String> kjlbMap, Map<String, String> jbshqkMap, Map<String, String> jyqkMap, Map<String, String> ylqkMap, Map<String, String> flqkMap);
}
