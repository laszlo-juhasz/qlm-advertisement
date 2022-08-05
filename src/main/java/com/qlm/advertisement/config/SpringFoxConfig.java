package com.qlm.advertisement.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.qlm.advertisement.controller"))            
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "QLM Advertisement demo project", 
          "Demo application for impleneting advertisements", 
          "1.0", 
          null, 
          new Contact("Laszlo Juhasz", null, "laszlo.juhasz.015@gmail.com"), 
          null, 
          null, 
          Collections.emptyList());
    }
}