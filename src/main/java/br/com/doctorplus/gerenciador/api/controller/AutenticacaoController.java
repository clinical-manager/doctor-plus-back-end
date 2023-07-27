package br.com.doctorplus.gerenciador.api.controller;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.annotations.SpringDocApiErrorResponseDTO;
import br.com.doctorplus.gerenciador.api.annotations.SpringDocApiResponseUtil;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.JwtViewDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.LoginUsuarioDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.SenhaDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.UsernameDTO;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.VerificaCodigoDTO;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiController(path = "autenticacao", name = "Autenticacao", description = "Api responsável por autenticar usuário.")
public interface AutenticacaoController {

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtViewDTO.class))})})
    @SpringDocApiErrorResponseDTO(summary = "Autenticar usuário com login e senha")
    ResponseEntity<JwtViewDTO> autenticar(@RequestBody LoginUsuarioDTO loginUsuarioDTO);

    @PostMapping("/esqueci-senha")
    @SpringDocApiResponseUtil(summary = "Api para recuperar senha pelo e-mail")
    ResponseEntity<ResponseSucesso> esqueciSenha(@RequestBody UsernameDTO loginUsuarioDTO);


    @PostMapping("/verifica-codigo")
    @SpringDocApiResponseUtil(summary = "Api para verificar codigo de confirmação")
    ResponseEntity<ResponseSucesso> verificaCodigo(@RequestBody VerificaCodigoDTO verificaCodigoDTO);

}
