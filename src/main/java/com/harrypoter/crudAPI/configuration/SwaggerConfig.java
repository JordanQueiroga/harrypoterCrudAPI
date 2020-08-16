/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.configuration;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author JORDAN QUEIROGA
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API Harry Potter").select()
                .apis(RequestHandlerSelectors.basePackage("com.harrypoter.crudAPI"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .globalResponseMessage(RequestMethod.GET, responseMessageBuilders());

    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Jordan Queiroga", null, "jordanpqueiroga@gmail.com");
        return new ApiInfoBuilder()
                .title("API Harry Potter Documentation")
                .description("Esta é a documentação interativa da API Harry Potter. Tente enviar algum request;")
                .contact(contact)
                .build();
    }

    private List<ResponseMessage> responseMessageBuilders() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(500)
                        .message("Internal server error")
                        .build(),
                new ResponseMessageBuilder()
                        .code(404)
                        .message("Not found")
                        .build(),
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad request")
                        .build()
        );
    }
}
