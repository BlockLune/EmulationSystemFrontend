package com.njupt.epsm.controller;

import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.common.PageDto;
import com.njupt.epsm.dto.ImageDto;
import com.njupt.epsm.req.ContainerIdReq;
import com.njupt.epsm.req.ContainerSelectReq;
import com.njupt.epsm.req.CreateTargetContainerReq;
import com.njupt.epsm.service.ContainerService;
import com.njupt.epsm.service.ImageService;
import com.njupt.epsm.util.RestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("container")
@Api(tags = "仿真靶场——容器管理")
public class ContainerController {

    private static final Logger log = LoggerFactory.getLogger(ContainerController.class);

    @Autowired
    private ImageService imageService;

    @Autowired
    private ContainerService containerService;


    @PostMapping("create")
    @ApiOperation("新增靶机容器")
    public void create(HttpServletResponse response, @RequestBody CreateTargetContainerReq req) {
        RestUtil.printData(response, containerService.createTargetContainer(req.getImageId()));
    }

    @PostMapping("selectContainerStatus")
    @ApiOperation("查询容器状态")
    public void create(HttpServletResponse response, @RequestBody ContainerIdReq req) {
        RestUtil.printData(response, containerService.selectContainerStatus(req.getContainerId()));
    }

    @PostMapping("selectTargetImage")
    @ApiOperation("查询靶机容器镜像")
    public void selectTargetImage(HttpServletResponse response) {
        List<ImageDto> imageDtos = imageService.selectTargetImage();
        RestUtil.printData(response, CommonResult.success(imageDtos));
    }

    @PostMapping("selectByPage")
    @ApiOperation("容器管理分页查询")
    public void selectByPage(HttpServletResponse response, @RequestBody ContainerSelectReq selectReq) {
        PageDto pageDto = containerService.selectByPage(selectReq);
        RestUtil.printData(response, CommonResult.success(pageDto));
    }

}
