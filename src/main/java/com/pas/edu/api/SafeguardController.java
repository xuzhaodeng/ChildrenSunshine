package com.pas.edu.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pas.edu.entity.Safegrard;
import com.pas.edu.entity.SafeguardInfo;
import com.pas.edu.entity.SafeguardList;
import com.pas.edu.entity.SafeguardSet;
import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.service.SafeguardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "保障评估接口--移动端", tags = "保障评估接口--移动端")
@RestController
@RequestMapping("api/safeguard")
public class SafeguardController {
	
	@Autowired
	private SafeguardService sfService;
	
	@ApiOperation(value = "评估保障列表接口", notes = "评估保障列表接口")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "uid", dataType = "int", required = true, value = "登录用户ID"),
		@ApiImplicitParam(paramType = "query", name = "searchTime", dataType = "int", required = false, value = "查询时间")
	})
	@RequestMapping(value = "datlst", method = RequestMethod.GET)
	public BaseResult<List<SafeguardList>> getSafeguard(@RequestParam(value = "uid", required = true) Integer uid,
			@RequestParam(value = "searchTime", required = false) String searchTime){
		BaseResult<List<SafeguardList>> br = new BaseResult<List<SafeguardList>>();
		br.setData(sfService.getSafeuardLst(uid, searchTime));
		return br;
	}
	
	@ApiOperation(value = "添加保障评估记录接口", notes = "添加保障评估记录接口")
	@RequestMapping(value = "datadd", method = RequestMethod.POST)
	public BaseResult<Object> addSafeguard(@RequestBody Safegrard safegrard){
		BaseResult<Object> br = new BaseResult<Object>();
		br.setData(sfService.insertSafeuard(safegrard));
		return br;
	}
	
	@ApiOperation(value = "更新保障评估记录接口", notes = "更新保障评估记录接口")
	@RequestMapping(value = "datupd", method = RequestMethod.POST)
	public BaseResult<Object> updSafeguard(@RequestBody Safegrard safegrard){
		BaseResult<Object> br = new BaseResult<Object>();
		br.setData(sfService.updateSafeuard(safegrard));
		return br;
	}
	
	@ApiOperation(value = "评估保障记录详细接口", notes = "评估保障记录详细接口")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "sfId", dataType = "int", required = true, value = "保障评估记录ID")
	})
	@RequestMapping(value = "datinfo", method = RequestMethod.GET)
	public BaseResult<SafeguardInfo> getSafeguardInfo(@RequestParam(value = "sfId", required = true) Integer sfId){
		BaseResult<SafeguardInfo> br = new BaseResult<SafeguardInfo>();
		br.setData(sfService.getSafeguardInfo(sfId));
		return br;
	}
	
	@ApiOperation(value = "设置保障评估为已经保障", notes = "设置保障评估为已经保障")
	@RequestMapping(value = "setAlrSafe", method = RequestMethod.POST)
	public BaseResult<Object> setSafeguardToHave(@RequestBody SafeguardSet safe){
		BaseResult<Object> br = new BaseResult<Object>();
		Integer rows =  sfService.setSafAlrCommit(safe.getSfId(), safe.getUid());
		if(rows > 0){
			br.setData(rows);
			return br;
		} else {
			br.setCode(-1);
			br.setData(rows);
			br.setMsg("处理失败");
			return br;
		}
		
	} 

}
