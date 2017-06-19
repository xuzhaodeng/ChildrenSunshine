package com.pas.edu.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "回访记录接口", tags = "回访记录相关接口")
@RestController
@RequestMapping("api/visitRecord")
public class VisitRecordController {
}
