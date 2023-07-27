package br.com.doctorplus.gerenciador.model.utils;

import br.com.doctorplus.gerenciador.config.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    public UserDetailsImpl getUsuario() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getEmail() {
        return getUsuario().getEmail();
    }

}
