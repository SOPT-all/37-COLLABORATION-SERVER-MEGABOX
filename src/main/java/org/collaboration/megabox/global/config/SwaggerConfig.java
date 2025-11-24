package org.collaboration.megabox.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "37-COLLABORATION-SERVER-MEGABOX Server API",
        version = "1.0",
        description = "DIVE SOPT 합동 세미나 모바일 웹 2조 메가박스 서버 API SWAGGER 입니다."
    ),
    servers = {
        @Server(
            url = "http://localhost:8080",
            description = "Local Server"
        ),
        @Server(
            url = "https://sopt37mega.kro.kr",
            description = "Prod Server"
        )
    }
)
public class SwaggerConfig { }