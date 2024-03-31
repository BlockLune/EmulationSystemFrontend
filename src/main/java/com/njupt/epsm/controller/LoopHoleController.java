package com.njupt.epsm.controller;


import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.req.*;
import com.njupt.epsm.service.LoopHoleService;
import com.njupt.epsm.util.RestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("loophole")
@Api(tags = "漏洞管理")
public class LoopHoleController {

    @Autowired
    private LoopHoleService loopHoleService;

    private static final Logger log = LoggerFactory.getLogger(LoopHoleController.class);

    @ApiModelProperty("创建漏洞分类")
    @PostMapping("category/create")
    public void createLoopHoleCategory(HttpServletResponse response, @RequestBody CreateLoopHoleCategoryReq createLoopHoleCategoryReq){
        try{
            Validate.notBlank(createLoopHoleCategoryReq.getCategoryName(),"分类名不可为空");
            Validate.notBlank(createLoopHoleCategoryReq.getDescr(),"分类详情不可为空");
            RestUtil.printData(response,loopHoleService.createLoopHoleCategory(createLoopHoleCategoryReq));
        }catch (Exception e){
            log.error("create loop hole category error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("删除漏洞分类")
    @PostMapping("category/delete")
    public void deleteLoopHoleCategoryById(HttpServletResponse response, @RequestBody DeleteLoopHoleCategoryReq deleteLoopHoleCategoryReq){
        try{
            Validate.notNull(deleteLoopHoleCategoryReq.getId(),"分类id不可为空");
            RestUtil.printData(response,loopHoleService.deleteLoopHoleCategoryById(deleteLoopHoleCategoryReq.getId()));
        }catch (Exception e){
            log.error("delete loop hole category error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("更新漏洞分类")
    @PostMapping("category/update")
    public void updateLoopHoleCategoryById(HttpServletResponse response, @RequestBody UpdateLoopHoleCategoryReq updateLoopHoleCategoryReq){
        try{
            Validate.notNull(updateLoopHoleCategoryReq.getId(),"分类id不可为空");
            RestUtil.printData(response,loopHoleService.updateLoopHoleCategoryById(updateLoopHoleCategoryReq));
        }catch (Exception e){
            log.error("update loop hole category error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("获取所有漏洞分类")
    @GetMapping("category/getall/{page}/{size}")
    public void getAllLoopHoleCategory(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size){
        try{
            Validate.notNull(page,"page不可为空");
            Validate.notNull(size,"size不可为空");
            RestUtil.printData(response,loopHoleService.getAllLoopHoleCategory(page,size));
        }catch (Exception e){
            log.error("get all loop hole category error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据分类id获取漏洞分类")
    @GetMapping("category/getbyid")
    public void getLoopHoleCategoryById(HttpServletResponse response, Long id){
        try{
            Validate.notNull(id,"id不可为空");
            RestUtil.printData(response,loopHoleService.getLoopHoleCategoryById(id));
        }catch (Exception e){
            log.error("get loop hole category by id error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("创建漏洞")
    @PostMapping("create")
    public void createLoopHole(HttpServletResponse response, @RequestBody CreateLoopHoleReq createLoopHoleReq){
        try{
            Validate.notNull(createLoopHoleReq.getCategoryId(),"分类id不可为空");
            Validate.notNull(createLoopHoleReq.getRelateExerciseId(),"演练id不可为空");
            Validate.notNull(createLoopHoleReq.getRelateContainerId(),"容器id不可为空");
            Validate.notNull(createLoopHoleReq.getRelateImageId(),"镜像id不可为空");
            Validate.notEmpty(createLoopHoleReq.getHoleName(),"漏洞名不可为空");
            Validate.notNull(createLoopHoleReq.getLevel(),"漏洞等级不可为空");
            Validate.notEmpty(createLoopHoleReq.getMetadata(),"元数据不可为空");
            RestUtil.printData(response,loopHoleService.createLoopHole(createLoopHoleReq));
        }catch (Exception e){
            log.error("create loop hole error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }


    @ApiModelProperty("删除漏洞")
    @PostMapping("delete")
    public void deleteLoopHoleById(HttpServletResponse response, @RequestBody DeleteLoopHoleCategoryReq deleteLoopHoleCategoryReq){
        try{
            Validate.notNull(deleteLoopHoleCategoryReq.getId(),"漏洞id不可为空");
            RestUtil.printData(response,loopHoleService.deleteLoopHoleById(deleteLoopHoleCategoryReq.getId()));
        }catch (Exception e){
            log.error("delete loop hole error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("更新漏洞")
    @PostMapping("update")
    public void updateLoopHoleById(HttpServletResponse response, @RequestBody UpdateLoopHoleReq updateLoopHoleReq){
        try{
            Validate.notNull(updateLoopHoleReq.getId(),"漏洞id不可为空");
            RestUtil.printData(response,loopHoleService.updateLoopHoleById(updateLoopHoleReq));
        }catch (Exception e){
            log.error("update loop hole error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据分类统计漏洞数量")
    @GetMapping("countbycategory")
    public void countLoopHoleByCategory(HttpServletResponse response, Long category_id){
        try{
            Validate.notNull(category_id,"分类id不可为空");
            RestUtil.printData(response,loopHoleService.countLoopHoleByCategory(category_id));
        }catch (Exception e){
            log.error("count loop hole by category error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("获取所有漏洞")
    @GetMapping("get/all/{page}/{size}")
    public void getAllLoopHole(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size){
        try{
            Validate.notNull(page,"page不可为空");
            Validate.notNull(size,"size不可为空");
            RestUtil.printData(response,loopHoleService.getAllLoopHole(page, size));
        }catch (Exception e){
            log.error("get all loop hole error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据分类获取漏洞")
    @GetMapping("get/bycategory/{page}/{size}")
    public void getLoopHoleByCategory(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size, Long category_id){
        try{
            Validate.notNull(category_id,"category_id不可为空");
            Validate.notNull(page,"page不可为空");
            Validate.notNull(size,"size不可为空");
            RestUtil.printData(response,loopHoleService.getLoopHoleByCategory(category_id, page, size));
        }catch (Exception e){
            log.error("get loop hole by category error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据演练获取漏洞")
    @GetMapping("get/byexercise/{page}/{size}")
    public void getLoopHoleByExercise(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size, Long exercise_id){
        try{
            Validate.notNull(exercise_id,"exercise_id不可为空");
            Validate.notNull(page,"page不可为空");
            Validate.notNull(size,"size不可为空");
            RestUtil.printData(response,loopHoleService.getLoopHoleByExercise(exercise_id, page, size));
        }catch (Exception e){
            log.error("get loop hole by exercise error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据镜像获取漏洞")
    @GetMapping("get/byimage/{page}/{size}")
    public void getLoopHoleByImage(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size, Long image_id){
        try{
            Validate.notNull(image_id,"image_id不可为空");
            Validate.notNull(page,"page不可为空");
            Validate.notNull(size,"size不可为空");
            RestUtil.printData(response,loopHoleService.getLoopHoleByImage(image_id, page, size));
        }catch (Exception e){
            log.error("get loop hole by image error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据容器获取漏洞")
    @GetMapping("get/bycontainer/{page}/{size}")
    public void getLoopHoleByContainer(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size, Long container_id){
        try{
            Validate.notNull(container_id,"container_id不可为空");
            Validate.notNull(page,"page不可为空");
            Validate.notNull(size,"size不可为空");
            RestUtil.printData(response,loopHoleService.getLoopHoleByContainer(container_id, page, size));
        }catch (Exception e){
            log.error("get loop hole by container error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据id获取漏洞")
    @GetMapping("get/byid")
    public void getLoopHoleById(HttpServletResponse response,Long id){
        try{
            Validate.notNull(id,"id不可为空");
            RestUtil.printData(response,loopHoleService.getLoopHoleById(id));
        }catch (Exception e){
            log.error("get loop hole by id error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }
}
