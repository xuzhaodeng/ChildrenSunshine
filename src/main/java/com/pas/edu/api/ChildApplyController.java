package com.pas.edu.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.RosterRequest;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.ChildApplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Author : 邓子
 * CreateDate : 2017/6/2 
 * Version : 2.0
 * Desc :  数据采集接口
 * Modified :
 */
@Api(value = "花名册", tags = "花名册接口")
@RestController
@RequestMapping("api/roster")
public class ChildApplyController extends BaseController {
	
	@Autowired
	ChildApplyService caService;
	
	@ApiOperation(value = "花名册添加", notes = "花名册添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result addRoster(@RequestBody @Valid ChildRoster childRoster) throws Exception {
        Result result = new Result();
        caService.addChildRoster(childRoster);
        return result;
    }
	
	@ApiOperation(value = "花名册更新", notes = "花名册更新")
	
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateRoster(@RequestBody @Valid ChildRoster childRoster) throws Exception {
        Result result = new Result();
        caService.updateChildApply(childRoster);
        return result;
    }
	
	
	@ApiOperation(value = "花名详情", notes = "获取花名册信息")
	@ApiImplicitParam(name = "childId", paramType = "path", value = "孩子id", required = true, dataType = "int")
	@RequestMapping(value = "detail/{childId}", method = RequestMethod.GET)
	public Result getRosterDetailed(@PathVariable Integer childId) throws Exception {
        Result result = new Result();
        result.setData(caService.getRosterInfoByChildId(childId));
        return result;
    }
	
	@ApiOperation(value = "获取村管端困境儿童列表", notes = "获取村管端困境儿童列表")
	@RequestMapping(value = "datlsts", method = RequestMethod.GET)
	public Result getRosterLsts(@RequestParam(value = "uid", required = true) Integer uid, 
			@RequestParam(value = "currPage", required = true) Integer currPage, 
			@RequestParam(value = "pageSize", required = true) Integer pageSize) throws Exception{
		Result result = new Result();
		result.setData(caService.getChildApplyLsts(uid, currPage, pageSize));
		return result;
	}
	
	@ApiOperation(value = "获取村管端困境儿童列表", notes = "获取村管端困境儿童列表")
	@RequestMapping(value = "deldata", method = RequestMethod.POST)
	public Result delRoster(@RequestParam(value = "uid", required = true)){
		
	}

}
