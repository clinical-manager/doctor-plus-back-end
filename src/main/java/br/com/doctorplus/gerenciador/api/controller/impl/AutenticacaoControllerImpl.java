package br.com.doctorplus.gerenciador.api.controller.impl;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.controller.AutenticacaoController;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.JwtViewDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.LoginUsuarioDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.VerificaCodigoDTO;
import br.com.doctorplus.gerenciador.model.services.AutenticacaoService;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import br.com.doctorplus.gerenciador.model.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@ApiController(path = "autenticacao", name = "Autenticacao", description = "Api responsável por autenticar usuário.")
public class AutenticacaoControllerImpl implements AutenticacaoController {

    private final AutenticacaoService service;

    @Override
    public ResponseEntity<JwtViewDTO> autenticar(LoginUsuarioDTO loginUsuarioDTO) {
        return new ResponseEntity<>(service.logar(loginUsuarioDTO), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ResponseUtil> esqueciSenha(LoginUsuarioDTO loginUsuarioDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseSucesso> verificaCodigo(VerificaCodigoDTO verificaCodigoDTO) {
        return new ResponseEntity<>(service.verificaCodigoConfirmacao(verificaCodigoDTO), HttpStatus.OK);
    }
}
