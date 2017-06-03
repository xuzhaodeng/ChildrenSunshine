package com.pas.edu.common;

/**
 * Author: eric
 * CreateDate: 2017/6/3
 * Modified: eric
 * ModifiedDate: 2017/6/3
 * Email: ericli_wang@163.com
 * Version: 1.0
 * Desc:
 */
public interface DictionaryHelper {
    int LEVEL_VILLAGE = 1;
    int LEVEL_TOWN = 2;
    int LEVEL_COUNTY = 3;
    int LEVEL_CITY = 4;


    int APPLY_NOT_AUDIT = 1;
    int APPLY_IN_AUDIT = 2;
    int APPLY_PASS = 3;
    int APPLY_REFUSE = 4;

    //困境类型-孤儿
    String INFO_DILEMMA_CATEGORY_ORPHAN = "A";
    String INFO_DILEMMA_CATEGORY_PROVERTY = "B";
    String INFO_DILEMMA_CATEGORY_DISABILITY = "C";
    String INFO_DILEMMA_CATEGORY_DIFFICULT = "D";
    String INFO_DILEMMA_CATEGORY_OTHER = "E";
}
