package com.pas.edu.service;

import com.pas.edu.entity.Datadict;

import java.util.List;
import java.util.Map;

public interface DatadictService {
    public Datadict getDatadict(int id);
    public Datadict getDatadictByCode(String type, String code);

    /**
     * 创建字典
     * @param datadict
     * @return 新的字典id
     */
    public int createDatadict(Datadict datadict);

    /**
     * 根据类别获取数据字典列表
     * @param type 类别
     * @return list
     */
    public List<Datadict> getDatadictList(String type);

    /**
     * 根据类别获取数据字典Map
     * @param type 类别
     * @return map
     */
    public Map<String,String> getDatadictMap(String type);

    /**
     * 获取所有字典
     * @return list
     */
    public List<Datadict> getAllDatadictList();
}
