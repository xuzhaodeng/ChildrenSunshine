package com.pas.edu.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "保障评估", tags = "保障评估相关接口")
@RestController
@RequestMapping("api/safeguard")
public class SafeguardController extends BaseController{
}
