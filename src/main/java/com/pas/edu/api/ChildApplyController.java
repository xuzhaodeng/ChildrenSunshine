package com.pas.edu.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pas.edu.entity.ChildRoster;
import com.pas.edu.entity.CompleteOrgan;
import com.pas.edu.entity.DelRosterRequest;
import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.ChildApplyService;
import com.pas.edu.service.OrganService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	
	@Autowired
	OrganService orginService;
	
	@ApiOperation(value = "花名册添加", notes = "花名册添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result addRoster(@RequestBody @Valid ChildRoster childRoster) throws Exception {
        Result result = new Result();
        if(caService.getRosterByChildIdCard(childRoster.getChildIdCard()) != null){
        	result.setCode(-1);
        	result.setData(null);
        	result.setMsg("儿童信息添加重复");
        } else {
        	 CompleteOrgan co =  orginService.getCompleteOrgan(childRoster.getOperateId());
             if(co != null){
             	 childRoster.setVillageId(co.getVillageOrg().getOrgId());
                  childRoster.setVillageName(co.getVillageOrg().getOrgName());
                  
                  childRoster.setTownId(co.getTownOrg().getOrgId());
                  childRoster.setTownName(co.getTownOrg().getOrgName());
                  
                  childRoster.setCountyId(co.getCountyOrg().getOrgId());
                  childRoster.setCountyName(co.getCountyOrg().getOrgName());
                  
                  childRoster.setCityId(co.getCityOrg().getOrgId());
                  childRoster.setCityName(co.getCityOrg().getOrgName());
                  
             }
             caService.addChildRoster(childRoster);
        }
       
        return result;
    }
	
	@ApiOperation(value = "花名册更新", notes = "花名册更新")
	
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateRoster(@RequestBody @Valid ChildRoster childRoster) throws Exception {
        Result result = new Result();
        CompleteOrgan co =  orginService.getCompleteOrgan(childRoster.getOperateId());
        if(co != null){
        	 childRoster.setVillageId(co.getVillageOrg().getOrgId());
             childRoster.setVillageName(co.getVillageOrg().getOrgName());
             
             childRoster.setTownId(co.getTownOrg().getOrgId());
             childRoster.setTownName(co.getTownOrg().getOrgName());
             
             childRoster.setCountyId(co.getCountyOrg().getOrgId());
             childRoster.setCountyName(co.getCountyOrg().getOrgName());
             
             childRoster.setCityId(co.getCityOrg().getOrgId());
             childRoster.setCityName(co.getCityOrg().getOrgName());
             
        }
        caService.updateChildApply(childRoster);
        return result;
    }
	
	
	@ApiOperation(value = "花名详情", notes = "获取花名册信息")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "childId", dataType = "int", required = true, value = "儿童Id")
	})
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public BaseResult<ChildRoster> getRosterDetailed(@RequestParam(value = "childId", required = true) Integer childId) throws Exception {
        BaseResult<ChildRoster> br = new BaseResult<ChildRoster>();
        br.setData(caService.getRosterInfoByChildId(childId));
        return br;
    }
	
	@ApiOperation(value = "获取村管端困境儿童列表", notes = "获取村管端困境儿童列表")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "uid", dataType = "int", required = true, value = "用户Id"),
		@ApiImplicitParam(paramType = "query", name = "currPage", dataType = "int", required = true, value = "当前页码 从零开始"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = true, value = "分页大小")
	})
	@RequestMapping(value = "datlsts", method = RequestMethod.GET)
	public BaseResult<List<ChildRoster>> getRosterLsts(@RequestParam(value = "uid", required = true) Integer uid, 
			@RequestParam(value = "currPage", required = true) Integer currPage, 
			@RequestParam(value = "pageSize", required = true) Integer pageSize) throws Exception{
		BaseResult<List<ChildRoster>> br = new BaseResult<List<ChildRoster>>();
		br.setData(caService.getChildApplyLsts(uid, currPage, pageSize));
		return br;
	}
	
	@ApiOperation(value = "获取村管端困境儿童列表--根据机构ID", notes = "获取村管端困境儿童列表--根据机构Id")
	@ApiImplicitParams(value = {
		@ApiImplicitParam(paramType = "query", name = "orgId", dataType = "int", required = true, value = "机构ID"),
		@ApiImplicitParam(paramType = "query", name = "currPage", dataType = "int", required = true, value = "当前页码 从零开始"),
		@ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = true, value = "分页大小")
	})
	@RequestMapping(value = "datlstsbyorgid", method = RequestMethod.GET)
	public BaseResult<List<ChildRoster>> getRosterLstsByOrgId(@RequestParam(value = "orgId", required = true) Integer orgId, 
			@RequestParam(value = "currPage", required = true) Integer currPage, 
			@RequestParam(value = "pageSize", required = true) Integer pageSize) throws Exception{
		BaseResult<List<ChildRoster>> br = new BaseResult<List<ChildRoster>>();
		br.setData(caService.getChildApplyLstsByOrgId(orgId, currPage, pageSize));
		return br;
	}
	
	@ApiOperation(value = "获取村管端困境儿童列表", notes = "获取村管端困境儿童列表")
	@RequestMapping(value = "deldata", method = RequestMethod.POST)
	public Result delRoster(@RequestBody DelRosterRequest delRoster){
		Result result = new Result();
		caService.delRoster(delRoster.getUid(), delRoster.getChildIds());
		return result;
	}

}
