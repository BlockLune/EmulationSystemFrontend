package com.njupt.epsm.controller;

import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.req.*;
import com.njupt.epsm.service.QemuService;
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
@RequestMapping("qemu")
@Api(tags = "设备漏洞挖掘")
public class QemuController {

    @Autowired
    private QemuService qemuService;

    private static final Logger log = LoggerFactory.getLogger(QemuController.class);

    @ApiModelProperty("上传漏洞挖掘插件")
    @PostMapping("upload")
    public void createQemuPlugin(HttpServletResponse response, CreateQemuPluginReq createQemuPluginReq){
        try{
            Validate.notBlank(createQemuPluginReq.getQemuPluginName(),"插件名不可为空");
            Validate.notBlank(createQemuPluginReq.getVersion(),"版本号不可为空");
            Validate.isTrue(!createQemuPluginReq.getFile().isEmpty(),"文件不能为空");
            RestUtil.printData(response,qemuService.createQemuPlugin(createQemuPluginReq));
        }catch (Exception e){
            log.error("upload qemu plugin error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据id删除插件")
    @PostMapping("delete")
    public void deleteQemuPluginById(HttpServletResponse response, @RequestBody DeleteQemuPluginReq deleteQemuPluginReq){
        try{
            Validate.notNull(deleteQemuPluginReq.getId(),"插件id不可为空");
            RestUtil.printData(response,qemuService.deleteQemuPluginById(deleteQemuPluginReq.getId()));
        }catch (Exception e){
            log.error("delete qemu plugin error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("获取所有插件")
    @GetMapping("getall/{page}/{size}")
    public void getAllQemuPlugin(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size){
        try{
            Validate.notNull(page,"page不可为空");
            Validate.notNull(size,"size不可为空");
            RestUtil.printData(response,qemuService.getAllQemuPlugin(page,size));
        }catch (Exception e){
            log.error("get all qemu plugin error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据id获取插件")
    @GetMapping("getbyid")
    public void getQemuPluginById(HttpServletResponse response, Long id){
        try{
            Validate.notNull(id,"id不可为空");
            RestUtil.printData(response,qemuService.getQemuPluginById(id));
        }catch (Exception e){
            log.error("get qemu plugin by id error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据名称获取插件")
    @GetMapping("getbyname/{page}/{size}")
    public void getQemuPluginByName(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size, String name){
        try{
            Validate.notBlank(name,"name不可为空");
            RestUtil.printData(response,qemuService.getQemuPluginByName(page,size,name));
        }catch (Exception e){
            log.error("get qemu plugin by name error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("获取所有插件任务")
    @GetMapping("getalltasks/{page}/{size}")
    public void getAllQemuTasks(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size){
        try{
            RestUtil.printData(response,qemuService.getAllQemuTasks(page,size));
        }catch (Exception e){
            log.error("get qemu task by name error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据插件id获取插件任务")
    @GetMapping("gettasksbypluginid/{page}/{size}")
    public void getQemuTasksByPluginId(HttpServletResponse response,@PathVariable("page") int page,@PathVariable("size") int size,Long pluginId){
        try{
            Validate.notNull(pluginId,"pluginId不可为空");
            RestUtil.printData(response,qemuService.getQemuTasksByPluginId(page,size,pluginId));
        }catch (Exception e){
            log.error("get qemu task by pluginId  error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据插件id创建插件任务（创建状态）")
    @PostMapping("createqemutask")
    public void getQemuTasksByPluginId(HttpServletResponse response, @RequestBody PluginIdReq pluginIdReq){
        try{
            Validate.notNull(pluginIdReq.getPluginId(),"pluginId不可为空");
            RestUtil.printData(response,qemuService.loadQemuPluginById(pluginIdReq.getPluginId()));
        }catch (Exception e){
            log.error("create qemu task error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("根据容器id获取并刷新插件状态（运行->结束）")
    @PostMapping("gettaskstatus")
    public void getQemuTaskByPluginId(HttpServletResponse response, @RequestBody QContainerIdReq containerIdReq){
        try{
            Validate.notNull(containerIdReq.getContainerId(),"containerId不可为空");
            RestUtil.printData(response,qemuService.getAndRefreshQemuContainerStatus(containerIdReq.getContainerId()));
        }catch (Exception e){
            log.error("get qemu task status error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("启动容器进程（仅创建状态可用，创建->运行）")
    @PostMapping("runtask")
    public void runQemuTask(HttpServletResponse response,@RequestBody QContainerIdReq containerIdReq){
        try{
            Validate.notNull(containerIdReq.getContainerId(),"containerId不可为空");
            RestUtil.printData(response,qemuService.runQemuPluginByContainerId(containerIdReq.getContainerId()));
        }catch (Exception e){
            log.error("run task error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("终止容器进程（仅运行状态可用，运行->结束）")
    @PostMapping("stoptask")
    public void stopQemuTask(HttpServletResponse response,@RequestBody QContainerIdReq containerIdReq){
        try{
            Validate.notNull(containerIdReq.getContainerId(),"containerId不可为空");
            RestUtil.printData(response,qemuService.stopQemuTask(containerIdReq.getContainerId()));
        }catch (Exception e){
            log.error("stop task error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("销毁容器进程（所有状态可用，xx->已结束并销毁）")
    @PostMapping("destroytask")
    public void destroyQemuTask(HttpServletResponse response,@RequestBody QContainerIdReq containerIdReq){
        try{
            Validate.notNull(containerIdReq.getContainerId(),"containerId不可为空");
            RestUtil.printData(response,qemuService.destroyQemuTask(containerIdReq.getContainerId()));
        }catch (Exception e){
            log.error("destroy task error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }

    @ApiModelProperty("删除任务（会先销毁容器）")
    @PostMapping("deletetask")
    public void deleteQemuTask(HttpServletResponse response,@RequestBody TaskIdReq taskIdReq){
        try{
            Validate.notNull(taskIdReq.getTaskId(),"taskId不可为空");
            RestUtil.printData(response,qemuService.deleteQemuTask(taskIdReq.getTaskId()));
        }catch (Exception e){
            log.error("delete task error",e);
            RestUtil.printData(response, CommonResult.failed(e.getMessage()));
        }
    }
}
