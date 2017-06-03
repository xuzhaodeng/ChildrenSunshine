package com.pas.edu.service.impl;

import com.pas.edu.entity.Summary;
import com.pas.edu.service.ReportService;
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
    @Override
    public List<Summary> getSummaryList(int orgId) {
        List<Summary> list=new ArrayList<Summary>(){
            {
                add(new Summary());
                add(new Summary());
                add(new Summary());
            }
        };
        return list;
    }
}
