package com.pas.edu.entity.common;

import java.util.HashMap;
import java.util.Map;

public class AuditStatus {
    private static Map<Integer,String> statusMap = new HashMap<Integer,String>(5);
    static{
        statusMap.put(1,"采集中");
        statusMap.put(2,"已提交");
        statusMap.put(3,"审核中");
        statusMap.put(4,"被驳回");
        statusMap.put(5,"已通过");
    }

    public static String getStatusTitle(Integer status) {
        return statusMap.get(status);
    }
}
