package com.pas.edu.service;

import com.pas.edu.entity.CompleteOrgan;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.OrganEditRequest;

/**
 * Author : eric
 * CreateDate : 2017/6/2  9:56
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public interface OrganService {
    Organ getOrganDetail(int orgId) throws Exception;

    CompleteOrgan getCompleteOrgan(int userId) throws Exception;

    long addOrgan(OrganEditRequest organEditRequest) throws Exception;

    void editOrgan(OrganEditRequest organEditRequest)throws Exception ;
}
