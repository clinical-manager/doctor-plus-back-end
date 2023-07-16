package br.com.doctorplus.gerenciador.model.helper;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderHelper {

    public String criptografarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
    public boolean senhasConferem(String newPassword, String oldPassword) {
        return BCrypt.checkpw(newPassword, oldPassword);
    }

    public boolean senhasNaoConferem(String newPassword, String oldPassword) {
        return !senhasConferem(newPassword, oldPassword);
    }
}
