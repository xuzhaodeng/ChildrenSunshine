package com.pas.edu.entity;

import lombok.Data;

/**
 * Author: eric
 * CreateDate: 2017/6/3
 * Modified: eric
 * ModifiedDate: 2017/6/3
 * Email: ericli_wang@163.com
 * Version: 1.0
 * Desc:
 */
@Data
public class CompleteOrgan {
    private Organ villageOrg;
    private Organ townOrg;
    private Organ countyOrg;
    private Organ cityOrg;
}
