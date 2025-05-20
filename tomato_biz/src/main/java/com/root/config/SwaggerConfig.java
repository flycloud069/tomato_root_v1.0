package com.root.config;

/**
 * @ClassName SwaggerConfig
 * @Author fuyunxiang
 * @Date 2021/4/1 11:08
 * @Description SwaggerConfig
 * @Version 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 *
 * @author wq
 * @since 2017-05-16
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //需要扫描的API(controller)包名
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(this.getParameter());
    }

    private List<Parameter> getParameter(){
        ParameterBuilder parameterBuilder =new ParameterBuilder();
        List<Parameter>parameters =new ArrayList<>();
        parameterBuilder.name("swagger").description("令牌").defaultValue("true").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameters.add(parameterBuilder.build());
        return parameters;
    }

    /**
     * 网页上的一些配置，标题，版本等等
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swaager Demo Rest API")
                .contact("可耳")
                .version("v1.0")
                .build();
    }


}