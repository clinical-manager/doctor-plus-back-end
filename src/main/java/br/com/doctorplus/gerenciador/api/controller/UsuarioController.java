package br.com.doctorplus.gerenciador.api.controller;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.annotations.SpringDocApiResponseUtil;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.SenhaDTO;
import br.com.doctorplus.gerenciador.model.dtos.usuario.CadastrarUsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@ApiController(path = "usuario", name = "Usuarios", description = "Api responsável pelas operações dos usuários")
public interface UsuarioController {

    @PostMapping("/profissional")
    @SpringDocApiResponseUtil(summary = "Cadastrar um usuário profissional no sistema")
    ResponseEntity<ResponseSucesso> cadastrar(@RequestBody @Valid CadastrarUsuarioDTO usuarioDTO);

    @GetMapping
    @Operation(summary = "Buscar usuários cadastrados 2", security = @SecurityRequirement(name = "Authentication"))
    @SpringDocApiResponseUtil
    ResponseEntity<List<Usuario>> buscarTodos();

    @PutMapping("/troca-senha")
    @Operation(summary = "Api para mudar a senha", security = @SecurityRequirement(name = "Authentication"))
    @SpringDocApiResponseUtil
    ResponseEntity<ResponseSucesso> trocarSenha(@RequestBody SenhaDTO senha);

}
