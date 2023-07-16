package br.com.doctorplus.gerenciador.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer",
        description = "Responsavel por receber o token jwt gerado para os endpoints do gerenciador clinico"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return (new OpenAPI())
                .info((new Info())
                        .title("DoctorPlus API")
                        .version("0.0.1")
                        .description("API desenvolvida como solução para demandas do gerenciador clinico")
                        .license((new License())
                                .name("Licença")
                                .url("")
                        )
                );
    }

}
