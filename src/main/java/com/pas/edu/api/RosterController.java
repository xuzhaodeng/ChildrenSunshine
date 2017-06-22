package com.pas.edu.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pas.edu.entity.common.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Author : 邓子
 * CreateDate : 2017/6/5 
 * Version : 2.0
 * Desc :  花名册查询列表
 * Modified :
 */
@Api(value = "花名册查询列表", tags = "花名册查询列表")
@RestController
@RequestMapping("api/roster")
public class RosterController extends BaseController {
	
	@ApiOperation(value = "获取村管端困境儿童列表", notes = "获取村管端困境儿童列表")
	@RequestMapping(value = "getlsts", method = RequestMethod.GET)
	public Result getRosterLsts(@RequestParam(value = "uid", required = true) Integer uid,
			@RequestParam(value = "orgId", required = true) Integer orgId,
			@RequestParam(value = "level", required = true) Integer level,
			@RequestParam(value = "currPage", required = true) Integer currPage, 
			@RequestParam(value = "pageSize", required = true) Integer pageSize) throws Exception{
		Result result = new Result();
		switch (level) {
			case 1: //村 -- 查看花名册
				
				break;
			case 2: //镇-- 查看村列表
				
				break;
			case 3: //县 --查看镇列表
				
				break;
			case 4: // 市 --查看市列表
				
				break;
			default:
				break;
		}
		return result;
	}
	
	@ApiOperation(value = "获取村管端困境儿童列表", notes = "获取村管端困境儿童列表")
	@RequestMapping(value = "gettest", method = RequestMethod.GET)
	public Result getRosterLsts(@RequestParam(value = "uid", required = true) Integer uid) throws Exception{
		Result result = new Result();
		return result;
	}

}
