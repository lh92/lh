package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 *
 * @author:lh
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(""))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("lh" , "url", "601700670@qq.com");

        /*
          String title,
          String description,
          String version,
          String termsOfServiceUrl,
          Contact contact,
          String license,
          String licenseUrl
         */

         return new ApiInfo("Swagger2简单用法","Swagger2简单用法","1.0","url",contact,"license","licenseUrl");

//        return new ApiInfoBuilder()
//                .title("Swagger2")
//                .description("Swagger2")
//                .termsOfServiceUrl("")
//                .contact("lh")
//                .version("1.0")
//                .build();
    }
}
