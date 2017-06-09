package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UploadHeadImgRequest {
    @NotEmpty
    @ApiModelProperty("请求的base64字符串")
    private String base64Data;

    @NotEmpty
    @ApiModelProperty("当前用户的id")
    private int userId;
}
