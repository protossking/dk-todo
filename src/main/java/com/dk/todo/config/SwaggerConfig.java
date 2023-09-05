package com.dk.todo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "TODO API Document",
                description = "TODO REST API DESCRIPTION",
                contact = @Contact(
                        name = "Yang",
                        email = "upgradmarine5@icloud.com"
                )
        )
)

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("Todo")
                .version(springdocVersion)
                .description("Todo API Server");


        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()

                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(Arrays.asList(securityRequirement))
                .info(info);
    }

}
