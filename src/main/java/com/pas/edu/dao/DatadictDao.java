package com.pas.edu.dao;

import com.pas.edu.entity.Datadict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DatadictDao {
    Datadict getDatadict(@Param("id") int id);
    Datadict getDatadictByCode(@Param("type") String type, @Param("code") String code);
    void createDatadict(Datadict datadict);
    List<Datadict> getDatadictList(@Param("type") String type);
    List<Datadict> getAllDatadictList();
}
