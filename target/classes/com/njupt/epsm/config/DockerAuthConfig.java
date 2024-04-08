package com.njupt.epsm.config;

import com.github.dockerjava.api.model.AuthConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DockerAuthConfig {

    @Value("${docker.auth.user-name}")
    private String userName;

    @Value("${docker.auth.password}")
    private String password;

    @Value("${docker.auth.registry-address}")
    private String registryAddress;

    @Bean
    public AuthConfig qemuAuthConfig(){
        return new AuthConfig()
                .withUsername(userName)
                .withPassword(password)
                .withRegistryAddress(registryAddress);
    }
}
