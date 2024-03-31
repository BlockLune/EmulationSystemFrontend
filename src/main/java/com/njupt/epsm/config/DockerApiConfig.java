package com.njupt.epsm.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author xiaoshu
 * @description docker client初始化
 * @date 2023年03月19日 15:31
 */
@Component
public class DockerApiConfig {

    private static final Logger log = LoggerFactory.getLogger(DockerApiConfig.class);

    @Value("${docker.url}")
    private String dockerUrl;

    @Bean
    public DockerClient dockerClient(){
        //String url="tcp://192.168.1.250:2375";
        DockerClient dockerClient = DockerClientBuilder.getInstance(dockerUrl).build();
        log.info("docker API初始化成功");
        return dockerClient;
    }

}
