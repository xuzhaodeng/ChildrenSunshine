package com.pas.edu.api;

import com.pas.edu.entity.UploadHeadImgRequest;
import com.pas.edu.entity.common.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.File;


@Api(value = "文件上传", tags = "文件上传接口")
@RestController
@RequestMapping("api/file")
public class FileUploadController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${uploadPath}")
    private String uploadPath;

    @Value("${imagePath}")
    private String imagePath;

    @ApiOperation(value = "上传头像", notes = "上传头像，数据为base64字符串")
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public BaseResult uploadHeadImg(@RequestBody UploadHeadImgRequest headImgRequest){
        BaseResult result = new BaseResult();
        try{
            logger.debug("上传文件的数据："+headImgRequest);
            String dataPrix = "";
            String data = "";

            logger.debug("对数据进行判断");
            if(headImgRequest.getBase64Data() == null || "".equals(headImgRequest.getBase64Data())){
                throw new Exception("上传失败，上传图片数据为空");
            }else{
                String [] d = headImgRequest.getBase64Data().split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                    throw new Exception("上传失败，数据不合法");
                }
            }

            logger.debug("对数据进行解析，获取文件名和流数据");
            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                result.setCode(500);
                result.setMsg("上传图片格式不合法");
                return result;
            }
            String tempFileName =  java.util.UUID.randomUUID() + suffix;

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            try{
                //使用apache提供的工具类操作流
                FileUtils.writeByteArrayToFile(new File(uploadPath, tempFileName), bs);
            }catch(Exception ee){
                logger.error("上传失败，写入文件失败，"+ee.getMessage());
                //throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
                result.setCode(500);
                result.setMsg("上传失败，写入文件失败，"+ee.getMessage());
                return result;
            }

            result.setMsg("上传成功");
            result.setData(imagePath+tempFileName);
            logger.debug("上传成功");
        }catch (Exception e) {
            logger.error("上传失败,"+e.getMessage());
            result.setCode(500);
            result.setMsg("上传失败,"+e.getMessage());
        }
        return result;
    }
}
