package com.pas.edu.service;

import com.pas.edu.entity.ApplyStatusReport;
import com.pas.edu.entity.ApplyReport;

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
    List<ApplyReport> getApplyReport(int orgId, int currLevel) throws Exception;

    ApplyStatusReport getApplyStatusReport(int orgId, int currLevel) throws Exception;
}
