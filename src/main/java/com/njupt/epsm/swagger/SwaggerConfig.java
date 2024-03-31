package com.njupt.epsm.swagger;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.njupt.epsm.controller")
                .title("电力仿真原型系统")
                .description("电力仿真原型系统")
                .contactName("liangw")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }

}
