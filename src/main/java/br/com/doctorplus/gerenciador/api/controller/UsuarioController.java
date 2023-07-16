package br.com.doctorplus.gerenciador.api.controller;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.annotations.SpringDocApiResponseUtil;
import br.com.doctorplus.gerenciador.model.dtos.usuario.UsuarioDTO;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import br.com.doctorplus.gerenciador.model.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiController(path = "usuarios", name = "Usuarios", description = "Api responsável pelas operações dos usuários")
public interface UsuarioController {

    @PostMapping
    @SpringDocApiResponseUtil(summary = "Cadastrar um novo usuário")
    ResponseEntity<ResponseSucesso> cadastrar(@RequestBody UsuarioDTO usuarioDTO);

}
