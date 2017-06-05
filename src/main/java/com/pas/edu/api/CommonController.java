package com.pas.edu.api;

import com.pas.edu.entity.common.Result;
import com.pas.edu.entity.common.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Eric on 2017/6/4.
 */
@Api(value = "公共信息", tags = "公共信息")
@RestController
@RequestMapping(value = "api/common")
public class CommonController extends BaseController {
    private static final String INFO = "<table>\n" +
            "    <tr>\n" +
            "        <td>200</td>\n" +
            "        <td>请求成功</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>400</td>\n" +
            "        <td>请求失败</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>401</td>\n" +
            "        <td>未授权</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>403</td>\n" +
            "        <td>禁止请求</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>500</td>\n" +
            "        <td>服务器内部错误</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>-1</td>\n" +
            "        <td>请求失败</td>\n" +
            "    </tr>\n" +
            "</table>";

    @ApiOperation(value = "公共信息", notes = INFO)
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String getCommon() {
        return "";
    }
}
