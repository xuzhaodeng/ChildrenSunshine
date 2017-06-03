package com.pas.edu.service;

import com.pas.edu.entity.Summary;

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
public interface ReportService {
    List<Summary> getSummaryList(int orgId);
}
