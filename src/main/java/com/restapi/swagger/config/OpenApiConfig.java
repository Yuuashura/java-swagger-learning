package com.restapi.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myCustomConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("👑 API Toko Paduka Yudis")
                        .description("Dokumentasi resmi Backend REST API untuk manajemen gudang.")
                        .version("1.0.1")
                        .contact(new Contact()
                                .name("Yudistira Syaputra")
                                .email("tzyudistira@gmai.com")
                                .url("https://github.com/Yuuashura")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Server Lokal (Development)"),
                        new Server().url("https://java-swagger-learning-production.up.railway.app").description("Server Live (Railway)")
                ));
    }
}