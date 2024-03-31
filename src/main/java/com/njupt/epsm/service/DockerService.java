package com.njupt.epsm.service;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class DockerService {

    private static final Logger log = LoggerFactory.getLogger(DockerService.class);

    /**
     * docker客户端映射
     */
    private static Map<String,DockerClient> dockerClients = new HashMap<>();

    /**
     * 连接信息
     */
    private final String DOCKER_CERT_CLASSPATH_1 = "classpath:certs/cert1";
    private final String DOCKER_HOST_1 = "tcp://121.40.100.72:2375";
    private final String DOCKER_APIVERSION_1 = "1.44";
    private final String DOCKER_KEY_1 = "default";

    /**
     * 初始化远程连接docker
     */
    public DockerService() {
        log.info("初始化DockerService");
        //不安全连接
        //this.connectClient("121.40.100.72","2375","default");
        //安全连接
        this.connectClientSafely(DOCKER_CERT_CLASSPATH_1,
                DOCKER_HOST_1,
                DOCKER_APIVERSION_1,
                DOCKER_KEY_1);
        /**
         * 其他docker连接
         * ---
         */
    }

    //    修改docker信息，直接实现远程访问
    //    进入docker服务器，修改配置文件
    //
    //    vi /lib/systemd/system/docker.service
    //    找到ExecStart 开头的配置，注释原配置 进行备份
    //    插入以下内容
    //    ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock
    //    保存退出
    //    systemctl daemon-reload
    //    service docker restart
    //    不安全连接docker
    public void connectClient(String host,String port,String key) {
        log.info("开始连接docker客户端");
        log.info("尝试连接tcp://"+host+":"+port);
        DockerClient dockerClient = DockerClientBuilder
                .getInstance("tcp://"+host+":"+port)
                .build();
        Info info =dockerClient.infoCmd().exec();
        String infoStr = JSONObject.toJSONString(info);
        log.info("连接tcp://"+host+":"+port+"成功");
        log.info("docker-"+key+" 的环境信息如下：");
        log.info(infoStr);
        this.dockerClients.put(key,dockerClient);
    }

    //    https://blog.csdn.net/qq_36956154/article/details/82180551
    /**
     * 安全连接
     * @param certClassPath like "classpath:xxx/xxx"
     * @param host like "tcp://xxx.xxx.xxx.xx:xxxx"
     * @param apiVersion see from docker version
     * @param key key of Map
     */
    public void connectClientSafely(String certClassPath,String host,String apiVersion,String key){
        log.info("开始安全连接");
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(certClassPath);
        String certPath = "";
        try{
            certPath = resource.getFile().getAbsolutePath();
        }catch (Exception e){
            log.error("获取不到证书地址");
        }
        //进行安全认证
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerTlsVerify(true)
                .withDockerCertPath(certPath).withDockerHost(host)
                .withDockerConfig(certPath).withApiVersion(apiVersion).withRegistryUrl("https://index.docker.io/v1/")
                .withRegistryUsername("dockeruser").withRegistryPassword("ilovedocker")
                .withRegistryEmail("dockeruser@github.com").build();
        DockerCmdExecFactory dockerCmdExecFactory =  new JerseyDockerCmdExecFactory()
                .withReadTimeout(1000)
                .withConnectTimeout(1000)
                .withMaxTotalConnections(100)
                .withMaxPerRouteConnections(10);
        //进行连接
        DockerClient dockerClient = DockerClientBuilder.getInstance(config).withDockerCmdExecFactory(dockerCmdExecFactory).build();
        log.info("docker "+key+" 连接成功");
        dockerClients.put(key,dockerClient);
        Info info = dockerClient.infoCmd().exec();
        log.info("docker info:",info);
    }

    public DockerClient getClient(String key) throws NoSuchElementException{
        DockerClient dockerClient;
        dockerClient = dockerClients.get(key);
        if (dockerClient == null){
            throw new NoSuchElementException();
        }
        return dockerClient;
    }

    public void listContainer(String dockerClientKey){
        DockerClient dockerClient = this.getClient(dockerClientKey);
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true)
                .exec();
        log.info("listContainer");
    }

    public List<Image> listImage(DockerClient dockerClient){
        return dockerClient.listImagesCmd().exec();
    }

    /**
     * 上传镜像（已测试），直接上传至镜像仓库，同时删除docker客户端加载的镜像
     * @param username
     * @param password
     * @param registryUrl
     * @param imageFile
     * @param tag
     * @throws InterruptedException
     */
    public String pushImage(String dockerClientKey, String username, String password,
                          String registryUrl,String registryNameSpace, MultipartFile imageFile, String imageName, String tag)
            throws IOException, InterruptedException {
        DockerClient dockerClient = this.getClient(dockerClientKey);
        // 构造新的镜像名
        AuthConfig authConfig = this.getAuthConfig(username,password,registryUrl);

        //加载镜像
        log.info("开始load镜像文件");
        final String[] imageId = {""};
        dockerClient.loadImageAsyncCmd(imageFile.getInputStream()).exec(new ResultCallback.Adapter<LoadResponseItem>(){
            @Override
            public void onNext(LoadResponseItem object) {
                log.info("load完成");
                String resId = dealWithLoadImageResStreamToImageId(dockerClient, object.getStream().trim());
                imageId[0] = resId;
                super.onNext(object);
            }

        }).awaitCompletion();

        if(StringUtils.isEmpty(imageId[0])){
            log.error("load 失败");
            return "";
        }

        //打标签
        this.tagImage(dockerClient,imageId[0],registryUrl+"/"+registryNameSpace+"/"+imageName,tag);

        //上传镜像
        dockerClient.pushImageCmd(registryUrl+"/"+registryNameSpace+"/"+imageName+":"+tag)
                .withAuthConfig(authConfig)
                .exec(new ResultCallback.Adapter<>()).awaitCompletion();
        //删除docker客户端中的镜像
        removeImage(dockerClient,imageId[0],true);

        return imageId[0];
    }

    //load后获取ID
    public String dealWithLoadImageResStreamToImageId(DockerClient dockerClient,String stream){
//        System.out.println(stream);
        log.info("开始解析imageid");
        String imageId = "";
        if(stream.contains("image ID:")){
            imageId = stream.substring(17);
        }else{
            String imageName = stream.substring(14).trim();
            List<Image> images = this.listImage(dockerClient);
            for(Image image:images){
                String[] repoTags = image.getRepoTags();
                boolean isFounded = false;
                for(String repoTag : repoTags){
//                    System.out.println(repoTag);
//                    System.out.println(imageName);
//                    System.out.println(StringUtils.equals(repoTag,imageName));
                    if (StringUtils.equals(repoTag,imageName)){
                        imageId = image.getId();
                        isFounded = true;
                    }
                }
                if(isFounded){
                    break;
                }
            }
        }
        return imageId.trim();
    }

    /**
     * 给镜像打标签
     * @param dockerClient
     * @param imageIdOrName
     * @param repository
     * @param tag
     */
    public void tagImage(DockerClient dockerClient,String imageIdOrName,String repository,String tag){
        dockerClient.tagImageCmd(imageIdOrName,repository,tag).exec();
    }

    public void pullImage(DockerClient dockerClient,String imageName,String repository,AuthConfig authConfig) throws InterruptedException {
        dockerClient.pullImageCmd(imageName)
                .withRepository(repository)
                .withAuthConfig(authConfig)
                .exec(new ResultCallback.Adapter<PullResponseItem>() {
                    @Override
                    public void onNext(PullResponseItem pullResponseItem) {

                    }
                }).awaitCompletion();
    }

    /**
     * 创建容器
     * @param dockerClient
     * @param imageName(镜像名称)
     * @param containerName(容器名称)
     * @param ports
     */
    public CreateContainerResponse createContainerFromImage(DockerClient dockerClient, String imageName,String containerName,Ports ports){
        CreateContainerCmd createContainerCmd = dockerClient.createContainerCmd(imageName);
        if(!StringUtils.isEmpty(containerName)){
            createContainerCmd.withName(containerName);
        }
        if(ports != null){
            createContainerCmd.withHostConfig(new HostConfig().withPortBindings(ports));
        }
        CreateContainerResponse container =createContainerCmd.exec();
        return container;
    }

    public InspectImageResponse inspectImage(DockerClient dockerClient,String imageId){
        return dockerClient.inspectImageCmd(imageId).exec();
    }

    public InspectContainerResponse inspectContainer(DockerClient dockerClient,String containerId){
        return dockerClient.inspectContainerCmd(containerId).exec();
    }

    /**
     * 停止容器
     * @param dockerClient
     * @param containerId
     */
    public void stopContainer(DockerClient dockerClient,String containerId){
        dockerClient.stopContainerCmd(containerId).exec();
    }

    /**
     * 启动容器
     * @param dockerClient
     * @param containerId
     */
    public void startContainer(DockerClient dockerClient,String containerId){
        dockerClient.startContainerCmd(containerId).exec();
    }

    public void removeContainer(DockerClient dockerClient,String containerId){
        InspectContainerResponse inspectContainerResponse = null;
        try{
            inspectContainerResponse = this.inspectContainer(dockerClient,containerId);
        }catch (Exception e){
            log.info("容器不存在或已销毁");
        }
        if(inspectContainerResponse != null){
            dockerClient.removeContainerCmd(containerId).exec();
        }
    }

    /**
     * 删除镜像
     * @param dockerClient
     * @param imageId
     * @param isForce 是否添加 -f 参数
     */
    public void removeImage(DockerClient dockerClient,String imageId,Boolean isForce){
        dockerClient.removeImageCmd(imageId).withForce(isForce).exec();
    }

    //用户配置
    public AuthConfig getAuthConfig(String userName,String password,String registryUrl){
        return new AuthConfig()
                .withUsername(userName)
                .withPassword(password)
                .withRegistryAddress(registryUrl);
    }


    public boolean isHaveImage(DockerClient dockerClient, String imageId) {
        List<Image> images = this.listImage(dockerClient);
        for(Image image : images){
            if(StringUtils.equals(imageId,image.getId())){
                return true;
            }
        }
        return false;
    }
}
