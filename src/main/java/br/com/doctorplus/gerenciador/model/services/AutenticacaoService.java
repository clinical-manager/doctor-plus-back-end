package br.com.doctorplus.gerenciador.model.services;


import br.com.doctorplus.gerenciador.config.security.JwtProvider;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.JwtViewDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.LoginUsuarioDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.SenhaDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.UsernameDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.VerificaCodigoDTO;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.repositories.UsuarioRepository;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import br.com.doctorplus.gerenciador.model.utils.ResponseUtil;
import br.com.doctorplus.gerenciador.model.utils.SecurityUtil;
import feign.Response;
import lombok.RequiredArgsConstructor;
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
    private final ResponseUtil response;
    private final UsuarioService usuarioService;
    private final EmailService emailService;

    public JwtViewDTO logar(LoginUsuarioDTO loginUsuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuarioDTO.username(), loginUsuarioDTO.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.geneareteJwt(authentication);
        return new JwtViewDTO(token);
    }

    public ResponseSucesso verificaCodigoConfirmacao(VerificaCodigoDTO verificaCodigoDTO) {
        usuarioService.verificarCodigoConfirmacao(verificaCodigoDTO);
        return response.buildResponse("usuario.verificado.sucesso", null);
    }

    public ResponseSucesso esqueciSenha(UsernameDTO loginUsuarioDTO) {
        Usuario usuario = usuarioService.buscarUsuarioPorUserName(loginUsuarioDTO.email());
        String jwt = jwtProvider.criarJwtEmail(usuario.getEmail(), usuario.getId());
        emailService.enviarEmailEsqueciSenha(usuario, jwt);
        return response.buildResponse("email.eviado.recuperacao.senha", null);
    }

}
