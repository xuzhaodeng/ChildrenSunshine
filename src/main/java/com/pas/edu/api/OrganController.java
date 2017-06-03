package com.pas.edu.api;

import com.pas.edu.entity.*;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.OrganService;
import com.pas.edu.service.UserService;
import com.pas.edu.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : eric
 * CreateDate : 2017/5/31  10:30
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  机构接口
 * Modified :
 */
@Api(value = "机构", tags = "机构接口")
@RestController
@RequestMapping("api/organ")
public class OrganController extends BaseController {
    @Autowired
    OrganService organService;

    @ApiOperation(value = "用户机构详情", notes = "获取机构以及父机构和子机构的信息")
    @ApiImplicitParam(name = "id", paramType = "path", value = "机构id", required = true, dataType = "int")
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public Result userOrgan(@PathVariable int id) throws Exception {
        Result result = new Result();
        Organ organ = organService.getOrganDetail(id);
        result.setData(organ);
        return result;
    }


    @ApiOperation(value = "机构", notes = "完整的机构信息")
    @ApiImplicitParam(name = "id", paramType = "path", value = "机构id", required = true, dataType = "int")
    @RequestMapping(value = "test/{id}", method = RequestMethod.GET)
    public Result test(@PathVariable int id) throws Exception {
        Result result = new Result();
        CompleteOrgan organ = organService.getCompleteOrgan(id);
        result.setData(organ);
        return result;
    }
}
