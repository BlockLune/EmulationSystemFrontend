package com.njupt.epsm.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.InspectImageResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njupt.epsm.common.CommonResult;
import com.njupt.epsm.config.DockerAuthConfig;
import com.njupt.epsm.mapper.QemuPluginMapper;
import com.njupt.epsm.mapper.QemuTaskMapper;
import com.njupt.epsm.po.QemuPlugin;
import com.njupt.epsm.po.QemuTask;
import com.njupt.epsm.req.CreateQemuPluginReq;
import com.njupt.epsm.util.SeqIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class QemuService {

    private final String username = "taopeiran";
    private final String password = "qwe123456";
    private final String qemuRegistryUrl = "registry.cn-shanghai.aliyuncs.com";

    private final String qemuRegistryNameSpace = "epsm-qemu";

    @Autowired
    private QemuPluginMapper qemuPluginMapper;

    @Autowired
    private QemuTaskMapper qemuTaskMapper;

    @Autowired
    private DockerService dockerService;

    @Autowired
    private DockerAuthConfig dockerAuthConfig;

    private static final Logger log = LoggerFactory.getLogger(QemuService.class);

    //创建插件镜像
    public CommonResult createQemuPlugin(CreateQemuPluginReq createQemuPluginReq) {
        QemuPlugin qemuPlugin = new QemuPlugin();
        qemuPlugin.setQemuPluginName(createQemuPluginReq.getQemuPluginName());
        qemuPlugin.setVersion(createQemuPluginReq.getVersion());
        qemuPlugin.setId(SeqIdUtil.getId());
        MultipartFile file = createQemuPluginReq.getFile();
        String tag = createQemuPluginReq.getVersion();
        String imageId = "";

        //上传镜像
        Boolean isUploadSuccess = false;
        try {
            log.info("push image starting");
            imageId = dockerService.pushImage("default",username, password,
                    qemuRegistryUrl,qemuRegistryNameSpace, file, createQemuPluginReq.getQemuPluginName(), tag);
            isUploadSuccess = StringUtils.isNotBlank(imageId);
            log.info("push image ending");
        }catch (Exception e){
            log.error("push to repository fail!");
        }

        //上传成功后插入数据库
        if(isUploadSuccess){
            qemuPlugin.setRepository(this.qemuRegistryUrl+"/"+this.qemuRegistryNameSpace+"/"+createQemuPluginReq.getQemuPluginName());
            qemuPlugin.setImageId(imageId);
            qemuPlugin.setCreateTime(new Date());
            qemuPlugin.setUpdateTime(new Date());

            int count = qemuPluginMapper.insert(qemuPlugin);
            if(count > 0){
                return CommonResult.success(qemuPlugin,"create plugin success!");
            }else{
                log.error("QemuPlugin Insert To DB Fail!");
                return CommonResult.failed("插入数据库失败");
            }
        }else{
            log.error("QemuPlugin Upload To DockerHub Fail!");
            return CommonResult.failed("上传到仓库失败！可能为网络缘故，请重新尝试。");
        }
    }

    /**
     * 分页获取所有插件
     * @param page
     * @param size
     * @return
     */
    public CommonResult getAllQemuPlugin(int page,int size){
        PageHelper.startPage(page,size);
        List<QemuPlugin> qemuPlugins = qemuPluginMapper.selectAll();
        PageInfo<QemuPlugin> qemuPluginPageInfo = new PageInfo<>(qemuPlugins);
        return CommonResult.success(qemuPluginPageInfo);
    }

    /**
     * 按id获取插件
     * @param id
     * @return
     */
    public CommonResult getQemuPluginById(Long id){
        QemuPlugin qemuPlugin = qemuPluginMapper.selectById(id);
        return CommonResult.success(qemuPlugin);
    }

    /**
     * 按名称搜索插件
     * @param page
     * @param size
     * @param name
     * @return
     */
    public CommonResult getQemuPluginByName(int page,int size,String name){
        PageHelper.startPage(page,size);
        List<QemuPlugin> qemuPlugins = qemuPluginMapper.selectByName(name);
        PageInfo<QemuPlugin> qemuPluginPageInfo = new PageInfo<>(qemuPlugins);
        return CommonResult.success(qemuPluginPageInfo);
    }

    /**
     * 按id删除插件
     * @param id
     * @return
     */
    public CommonResult deleteQemuPluginById(Long id){
        log.info("开始删除插件");
        DockerClient dockerClient = dockerService.getClient("default");
        //清理相关容器再删除任务
        QemuPlugin qemuPlugin = qemuPluginMapper.selectById(id);
        List<QemuTask> tasks = qemuTaskMapper.selectByPluginId(id);
        log.info("删除相关任务及其容器");
        if(tasks != null && tasks.size()>0){
            for(QemuTask qemuTask : tasks){
                dockerService.removeContainer(dockerClient,qemuTask.getContainerId());
                qemuTaskMapper.deleteById(qemuTask.getId());
            }
        }
        //删除docker镜像
        InspectImageResponse inspectImageResponse = null;
        try {
           inspectImageResponse = dockerService.inspectImage(dockerClient,qemuPlugin.getImageId());
        }catch (Exception e){
            log.info("docker没有相关镜像");
        }
        if(inspectImageResponse != null){
            dockerService.removeImage(dockerClient,qemuPlugin.getImageId(),true);
        }
        //删除插件数据
        int count = qemuPluginMapper.deleteById(id);
        if(count > 0){
            return CommonResult.success("delete successful");
        }else{
            return CommonResult.failed("delete failure");
        }
    }

    /**
     * 获取所有挖掘任务
     * @param page
     * @param size
     * @return
     */
    public CommonResult getAllQemuTasks(int page,int size){
        PageHelper.startPage(page,size);
        List<QemuTask> qemuTasks = qemuTaskMapper.selectAll();
        PageInfo<QemuTask> qemuTaskPageInfo = new PageInfo<>(qemuTasks);
        return CommonResult.success(qemuTaskPageInfo);
    }

    /**
     * 根据插件id获取挖掘任务
     * @param page
     * @param size
     * @param pluginId
     * @return
     */
    public CommonResult getQemuTasksByPluginId(int page,int size,Long pluginId){
        PageHelper.startPage(page,size);
        List<QemuTask> qemuTasks = qemuTaskMapper.selectByPluginId(pluginId);
        PageInfo<QemuTask> qemuTaskPageInfo = new PageInfo<>(qemuTasks);
        return CommonResult.success(qemuTaskPageInfo);
    }



    /**
     * 将某插件加入挖掘任务
     * @param id
     * @return CommonResult
     */
    public CommonResult loadQemuPluginById(Long id) throws InterruptedException {
        DockerClient dockerClient = dockerService.getClient("default");
        QemuPlugin qemuPlugin = qemuPluginMapper.selectById(id);
        if (qemuPlugin == null){
            log.error("没有相关镜像数据");
            return CommonResult.failed("获取相关镜像失败");
        }
        String imageId = qemuPlugin.getImageId();
        String repository = qemuPlugin.getRepository();
        String version = qemuPlugin.getVersion();
        if(!dockerService.isHaveImage(dockerClient,imageId)){
            log.error("开始拉取镜像");
            dockerService.pullImage(dockerClient,
                    repository+":"+version,
                    repository,
                    dockerAuthConfig.qemuAuthConfig());
        }
        log.info("开始创建容器");
        CreateContainerResponse createContainerResponse = dockerService.createContainerFromImage(dockerClient,imageId,null,null);
        QemuTask qemuTask = new QemuTask();
        qemuTask.setId(SeqIdUtil.getId());
        qemuTask.setPluginId(id);
        qemuTask.setTaskStatus(QemuTask.TaskStatus.CREATED);
        qemuTask.setCreateTime(new Date());
        qemuTask.setUpdateTime(new Date());
        qemuTask.setContainerId(createContainerResponse.getId());
        int count = qemuTaskMapper.insert(qemuTask);
        if(count > 0){
            log.info("创建成功");
            return CommonResult.success(qemuTask,"创建成功");
        }else{
            return CommonResult.failed("插入数据库失败");
        }

    }

    /**
     * 执行插件任务
     * @param containerId
     * @return
     */
    public CommonResult runQemuPluginByContainerId(String containerId){
        DockerClient dockerClient = dockerService.getClient("default");
        QemuTask qemuTask = new QemuTask();
        qemuTask.setContainerId(containerId);
        log.info("开始启动容器");
        dockerService.startContainer(dockerClient,containerId);
        qemuTask.setTaskStatus(QemuTask.TaskStatus.EXECUTING);
        int count = qemuTaskMapper.updateStatusByContainerId(qemuTask);
        if(count > 0){
            return CommonResult.success("启动容器成功");
        }else{
            return CommonResult.failed("插入数据失败");
        }
    }

    /**
     * 强制停止挖掘任务
     * @param containerId
     * @return
     */
    public CommonResult stopQemuTask(String containerId){
        DockerClient dockerClient = dockerService.getClient("default");
        log.info("开始停止容器");
        InspectContainerResponse inspectContainerResponse = dockerService.inspectContainer(dockerClient,containerId);
        if(!StringUtils.equals(inspectContainerResponse.getState().getStatus().trim(),"running")
                && !StringUtils.equals(inspectContainerResponse.getState().getStatus().trim(),"existed")){
            return CommonResult.failed("非running状态不可操作，请刷新状态");
        }
        if(StringUtils.equals(inspectContainerResponse.getState().getStatus().trim(),"running")){
            dockerService.stopContainer(dockerClient,containerId);
        }
        QemuTask qemuTask = new QemuTask();
        qemuTask.setContainerId(containerId);
        qemuTask.setTaskStatus(QemuTask.TaskStatus.FINISHED);
        int count = qemuTaskMapper.updateStatusByContainerId(qemuTask);
        if(count > 0){
            return CommonResult.success("停止容器成功");
        }else{
            return CommonResult.failed("插入数据失败");
        }
    }

    /**
     * 销毁插件容器（强制）
     * @param containerId
     * @return
     */
    public CommonResult destroyQemuTask(String containerId){
        DockerClient dockerClient = dockerService.getClient("default");
        log.info("开始销毁容器");
        dockerService.removeContainer(dockerClient,containerId);
        QemuTask qemuTask = new QemuTask();
        qemuTask.setContainerId(containerId);
        qemuTask.setTaskStatus(QemuTask.TaskStatus.DESTROYED);
        int count = qemuTaskMapper.updateStatusByContainerId(qemuTask);
        if(count > 0){
            return CommonResult.success();
        }else{
            return CommonResult.failed("插入数据失败");
        }
    }

    /**
     * 删除挖掘任务
     * @param taskId
     * @return
     */
    public CommonResult deleteQemuTask(Long taskId){
        log.info("删除qemu任务："+taskId);
        DockerClient dockerClient = dockerService.getClient("default");
        QemuTask qemuTask = qemuTaskMapper.selectById(taskId);
        //销毁容器
        log.info("删除qemu任务-移除容器"+qemuTask.getContainerId());
        try {
            dockerService.removeContainer(dockerClient,qemuTask.getContainerId());
            log.info("移除成功");
        }catch (Exception e){
            log.info("容器不存在，或已销毁");
        }
        int count = qemuTaskMapper.deleteById(taskId);
        if(count > 0){
            return CommonResult.success();
        }else{
            return CommonResult.failed("删除失败");
        }
    }

    /**
     * 查询容器状态，并更改数据库任务状态
     * @param containerId
     * @return
     */
    public CommonResult getAndRefreshQemuContainerStatus(String containerId){
        DockerClient dockerClient = dockerService.getClient("default");
        String status ="";
        try{
            status= dockerService.inspectContainer(dockerClient,containerId).getState().getStatus().trim();
        }catch (Exception e){
            log.error("不存在该容器或，容器已被移除");
        }
        System.out.println(status);
        QemuTask qemuTask = new QemuTask();
        if(StringUtils.equals(status,"created")){
            qemuTask.setContainerId(containerId);
            qemuTask.setTaskStatus(QemuTask.TaskStatus.CREATED);
        } else if (StringUtils.equals(status,"running")) {
            qemuTask.setContainerId(containerId);
            qemuTask.setTaskStatus(QemuTask.TaskStatus.EXECUTING);
        } else if (StringUtils.equals(status,"exited")){
            qemuTask.setContainerId(containerId);
            qemuTask.setTaskStatus(QemuTask.TaskStatus.FINISHED);
        } else if (StringUtils.isEmpty(status)){
            qemuTask = null;
        }
        if(qemuTask != null){
            int count = qemuTaskMapper.updateStatusByContainerId(qemuTask);
            if(count > 0){
                return CommonResult.success(qemuTask.getTaskStatus(),"已更新");
            }else{
                return CommonResult.failed("变更数据失败");
            }
        }else{
            return CommonResult.failed("不存在该容器或，或容器已被移除");
        }
    }

}
