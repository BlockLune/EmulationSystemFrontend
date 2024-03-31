package com.njupt.epsm.filter;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Data
@ConfigurationProperties(prefix = "secure.ignored")
@Configuration
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

}
