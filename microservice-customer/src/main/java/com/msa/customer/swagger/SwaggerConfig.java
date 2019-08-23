package com.msa.customer.swagger;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.msa.customer.controller"))
                        .paths(PathSelectors.any())
                        .build().apiInfo(new ApiInfo("Customer Service Api Documentation",
                        		"Documentation generated via Swagger", 
                        		"1.0.0",null, new Contact("Hari Kishore", "https://www.github.com/", "mhari.tech@gmail.com"), null,null));
    }
	
	
	
}
