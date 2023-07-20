package br.com.doctorplus.gerenciador.model.services;


import br.com.doctorplus.gerenciador.config.security.JwtProvider;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.JwtViewDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.LoginUsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public JwtViewDTO logar(LoginUsuarioDTO loginUsuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuarioDTO.email(), loginUsuarioDTO.senha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.geneareteJwt(authentication);
        return new JwtViewDTO(token);
    }

}
