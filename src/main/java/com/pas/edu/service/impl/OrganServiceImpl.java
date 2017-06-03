package com.pas.edu.service.impl;

import com.pas.edu.dao.OrganDao;
import com.pas.edu.dao.UserDao;
import com.pas.edu.entity.CompleteOrgan;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.User;
import com.pas.edu.exception.CommonException;
import com.pas.edu.service.OrganService;
import com.sun.org.apache.xpath.internal.operations.Or;
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
