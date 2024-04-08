package com.njupt.epsm.controller;

import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.req.ImageIdReq;
import com.njupt.epsm.req.ImageSelectReq;
import com.njupt.epsm.service.ImageService;
import com.njupt.epsm.util.RestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("image")
@Api(tags = "仿真靶场——镜像管理")
public class ImageController {

    @Autowired
    private ImageService imageService;

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @PostMapping("selectByPage")
    @ApiOperation("列表查询")
    public void selectByPage(HttpServletResponse response, @RequestBody ImageSelectReq selectReq) {
        try {
            RestUtil.printData(response, imageService.selectByPage(selectReq));
        } catch (Exception e) {
            log.error("selectByPage error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @PostMapping("uploadImage")
    @ApiOperation("上传镜像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageName", value = "镜像名称"),
            @ApiImplicitParam(name = "version", value = "版本号"),
            @ApiImplicitParam(name = "imageType", value = "镜像类型 0:漏洞挖掘镜像 1:包含漏洞数据镜像 2:网络攻击镜像  3:网络防御镜像  4:靶机镜像"),
            @ApiImplicitParam(name = "file", value = "源文件")
    })
    public void uploadImage(HttpServletResponse response,
                            @RequestParam("imageName") String imageName,
                            @RequestParam("version") String version,
                            @RequestParam("imageType") String imageType,
                            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            RestUtil.printData(response, imageService.uploadImage(imageName, version, imageType, file));
        } catch (Exception e) {
            log.error("uploadImage error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @PostMapping("deleteImage")
    @ApiOperation("删除镜像")
    public void deleteImage(HttpServletResponse response, @RequestBody ImageIdReq imageIdReq) {
        try {
            RestUtil.printData(response, imageService.deleteImage(imageIdReq.getId()));
        } catch (Exception e) {
            log.error("deleteImage error", e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }
}
