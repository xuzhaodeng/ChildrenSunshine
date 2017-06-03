package com.pas.edu.service.impl;

import com.pas.edu.dao.ChildApplyDao;
import com.pas.edu.dao.OrganDao;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.Summary;
import com.pas.edu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: eric
 * CreateDate: 2017/6/3
 * Modified: eric
 * ModifiedDate: 2017/6/3
 * Email: ericli_wang@163.com
 * Version: 1.0
 * Desc:
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ChildApplyDao childApplyDao;

    @Autowired
    OrganDao organDao;


    @Override
    public List<Summary> getSummaryList(int orgId) throws Exception {
        //获取子机构
        List<Organ> childOrgan = organDao.getChildOrganList(orgId);
        List<Summary> list = new ArrayList<Summary>();
        //遍历子机构的汇总
        for (Organ organ : childOrgan) {
            Summary summary = new Summary();
            summary.setOrgId(organ.getOrgId());
            summary.setOrgName(organ.getOrgName());
            list.add(summary);
        }
        return list;
    }
}
