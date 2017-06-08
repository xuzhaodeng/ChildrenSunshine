package com.pas.edu.service.impl;

import com.pas.edu.dao.DatadictDao;
import com.pas.edu.entity.Datadict;
import com.pas.edu.service.DatadictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DatadictServiceImpl implements DatadictService {
    @Autowired
    private DatadictDao datadictDao;

    @Override
    public Datadict getDatadict(int id) {
        return datadictDao.getDatadict(id);
    }

    @Override
    public Datadict getDatadictByCode(String type, String code) {
        return datadictDao.getDatadictByCode(type,code);
    }

    @Override
    public int createDatadict(Datadict datadict) {
        datadictDao.createDatadict(datadict);
        return datadict.getId();
    }

    @Override
    public List<Datadict> getDatadictList(String type) {
        return datadictDao.getDatadictList(type);
    }

    @Override
    public Map<String, String> getDatadictMap(String type) {
        List<Datadict> datadictList = datadictDao.getDatadictList(type);
        Map<String,String> map = new HashMap<String,String>();
        for(Datadict item:datadictList) {
            map.put(item.getCode(),item.getTitle());
        }
        return map;
    }

    @Override
    public List<Datadict> getAllDatadictList() {
        return datadictDao.getAllDatadictList();
    }
}
