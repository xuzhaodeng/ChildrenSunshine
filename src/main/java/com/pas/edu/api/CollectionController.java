package com.pas.edu.api;

import com.pas.edu.entity.ApplyChildRequest;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.CollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Author: eric
 * CreateDate: 2017/6/3
 * Modified: eric
 * ModifiedDate: 2017/6/3
 * Email: ericli_wang@163.com
 * Version: 1.0
 * Desc:
 */
@RestController
@Api(value = "示例", tags = "示例采集接口")
@RequestMapping(value = "api/collection")
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @ApiOperation(value = "保存", notes = "")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody @Valid ApplyChildRequest applyChildRequest) throws Exception {
        Result result = new Result();
        collectionService.save(applyChildRequest);
        return result;
    }
}
