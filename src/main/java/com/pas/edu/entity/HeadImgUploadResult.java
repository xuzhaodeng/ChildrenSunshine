package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HeadImgUploadResult {
    /**
     * 前缀路径
     */
    @ApiModelProperty("前缀路径")
    private String headImgPath;
    /**
     * 图片文件名
     */
    @ApiModelProperty("图片文件名")
    private String headImg;
}
