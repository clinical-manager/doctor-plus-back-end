package br.com.doctorplus.gerenciador.api.controller;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.annotations.SpringDocApiResponseUtil;
import br.com.doctorplus.gerenciador.model.dtos.usuario.CadastrarUsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@ApiController(path = "usuario", name = "Usuarios", description = "Api responsável pelas operações dos usuários")
public interface UsuarioController {

    @PostMapping("/profissional")
    @SpringDocApiResponseUtil(summary = "Cadastrar um usuário profissional no sistema")
    ResponseEntity<ResponseSucesso> cadastrar(@RequestBody CadastrarUsuarioDTO usuarioDTO);

    @GetMapping
    @SpringDocApiResponseUtil(summary = "Buscar usuários cadastrados")
    @SecurityRequirement(name = "Authorization")
    ResponseEntity<List<Usuario>> buscarTodos();

}
