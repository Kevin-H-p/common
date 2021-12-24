package com.kevin.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String SEPARATOR = ";";

    @Bean
    public Docket createRestApi() {
        ParameterBuilder par = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        par.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(par.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.enable(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kevin.common.controller"))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("SpringBoot中使用Swagger2接口规范")
                //文档描述
                .description("接口说明")
                //服务条款URL
                .termsOfServiceUrl("http://localhost:8080/")
                //版本号
                .version("1.0.0")
                .build();
    }
}
