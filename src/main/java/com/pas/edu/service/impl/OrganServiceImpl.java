package com.pas.edu.service.impl;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleGotoStatement;
import com.pas.edu.common.UserHelper;
import com.pas.edu.dao.OrganDao;
import com.pas.edu.dao.UserDao;
import com.pas.edu.entity.CompleteOrgan;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.OrganEditRequest;
import com.pas.edu.entity.User;
import com.pas.edu.exception.CommonException;
import com.pas.edu.service.OrganService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/6/2  9:56
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
@Service
public class OrganServiceImpl implements OrganService {
    private static final Logger logger = LoggerFactory.getLogger(OrganServiceImpl.class);

    @Autowired
    OrganDao organDao;

    @Autowired
    UserDao userDao;

    @Override
    public Organ getOrganDetail(int orgId) throws Exception {
        Organ currentOrgan = organDao.getOrgan(orgId);
        if (currentOrgan == null) {
            throw new CommonException("orgId不存在");
        }
        Organ temp = currentOrgan;
        //获取上级机构
        while (temp.getOrgLevel() > 1) {
            Organ parentOrgan = organDao.getOrgan(temp.getParentOrgId());
            temp.setParentOrgan(parentOrgan);
            temp = parentOrgan;
        }
        getChildrenOrgan(currentOrgan);
        return currentOrgan;
    }

    @Override
    public CompleteOrgan getCompleteOrgan(int userId) throws Exception {
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new CommonException("用户不存在");
        }
        CompleteOrgan completeOrgan = new CompleteOrgan();
        Organ villageOrgan = organDao.getOrgan(user.getOrgId());
        if (villageOrgan == null) {
            throw new CommonException("villageOrgan 不存在");
        }
        Organ townOrgan = organDao.getOrgan(villageOrgan.getParentOrgId());
        if (townOrgan == null) {
            throw new CommonException("townOrgan 不存在");
        }
        Organ countyOrgan = organDao.getOrgan(townOrgan.getParentOrgId());
        if (countyOrgan == null) {
            throw new CommonException("countyOrgan 不存在");
        }
        Organ cityOrgan = organDao.getOrgan(countyOrgan.getParentOrgId());
        if (cityOrgan == null) {
            throw new CommonException("cityOrgan 不存在");
        }
        completeOrgan.setVillageOrg(villageOrgan);
        completeOrgan.setTownOrg(townOrgan);
        completeOrgan.setCountyOrg(countyOrgan);
        completeOrgan.setCityOrg(cityOrgan);
        return completeOrgan;
    }

    /**
     * 添加机构
     *
     * @param organEditRequest
     * @throws Exception
     */
    @Override
    public long addOrgan(OrganEditRequest organEditRequest) throws Exception {
        checkOperateUser(organEditRequest);
        //查询机构名称和机构号是否重复
        Organ organ = organDao.getOrganByNameOrCode(
                organEditRequest.getOrgName(),
                organEditRequest.getOrgCode());
        if (organ != null) {
            if (organEditRequest.getOrgName().equals(organ.getOrgName())) {
                throw new CommonException("机构名称重复");
            }
            if (organEditRequest.getOrgCode().equals(organ.getOrgCode())) {
                throw new CommonException("机构号重复");
            }
        }
        //查询父机构是否存在
        if (organDao.getOrgan(organEditRequest.getParentOrgId()) == null) {
            throw new CommonException("父机构不存在");
        }
        organDao.add(organEditRequest);
        //返回orgId
        return organEditRequest.getOrgId();
    }

    @Override
    public void editOrgan(OrganEditRequest organEditRequest) throws Exception {
        checkOperateUser(organEditRequest);
        Organ organ = organDao.getOrgan(organEditRequest.getOrgId());
        if (organ == null) {
            throw new CommonException("机构不存在");
        }
        organDao.updateOrgan(organEditRequest);
    }

    /**
     * 检查操作用户是否合法
     *
     * @param organEditRequest
     * @throws Exception
     */
    private void checkOperateUser(OrganEditRequest organEditRequest) throws Exception {
        User user = userDao.getUserById(organEditRequest.getOperateUserId());
        if (user == null) {
            throw new CommonException("操作用户不在");
        }
        if (user.getOrgLevel() != UserHelper.LEVEL_CITY) {
            throw new CommonException("非市级别权限，无法添加机构");
        }
        if (user.getOrgLevel() >= organEditRequest.getOrgLevel()) {
            throw new CommonException("机构级别错误");
        }
    }

    /**
     * 获取子机构
     *
     * @param organ
     */
    private void getChildrenOrgan(Organ organ) {
        if (organ.getOrgLevel() < 4) {
            //获取子机构
            List<Organ> childOrganList = organDao.getChildOrganList(organ.getOrgId());
            if (!CollectionUtils.isEmpty(childOrganList)) {
                organ.setChildOrganList(childOrganList);
                for (Organ childOrgan : childOrganList) {
                    //判断子机构的level是否大于父机构,防止数据错误出现死循环
                    if (childOrgan.getOrgLevel() > organ.getOrgLevel())
                        getChildrenOrgan(childOrgan);
                }
            }
        }
    }
}
