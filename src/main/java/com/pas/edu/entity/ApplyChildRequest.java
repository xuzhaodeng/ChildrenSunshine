package com.pas.edu.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

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
public class ApplyChildRequest {
    @NotEmpty
    private String childName;
    @NotEmpty
    private String childIdCard;
}
