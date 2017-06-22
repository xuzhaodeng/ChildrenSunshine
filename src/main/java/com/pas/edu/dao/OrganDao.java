package com.pas.edu.dao;

import com.pas.edu.entity.Organ;
import com.pas.edu.entity.OrganEditRequest;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/6/2  9:42
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public interface OrganDao {
    List<Organ> getChildOrganList(@Param("orgId") int orgId);

    Organ getOrgan(@Param("orgId") int orgId);

    void add(OrganEditRequest organ);

    Organ getOrganByNameOrCode( @Param("orgName") String orgName
            , @Param("orgCode") String orgCode);
}
