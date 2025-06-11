package com.EduTech.EduTech.config;
import  io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API 2026 Gestion de cursos")
                        .version("1.0")
                        .description("Documentacion de la API para la gestion de cursos"));
    }
}
