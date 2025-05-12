package net.bsfconsulting.locationauto.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LocationAuto API")
                        .version("1.0")
                        .description("Doucumentation de l'API pour le projet LocationAuto"));
    }
}
