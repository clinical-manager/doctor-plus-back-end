package br.com.doctorplus.gerenciador.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "doctor-plus.security.properties", ignoreUnknownFields = false)
public class SecurityProperties {

    private String[] whitelist;
}
