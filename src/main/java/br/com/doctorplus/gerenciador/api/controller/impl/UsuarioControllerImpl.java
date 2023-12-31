package br.com.doctorplus.gerenciador.api.controller.impl;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.controller.UsuarioController;
import br.com.doctorplus.gerenciador.model.dtos.autenticacao.SenhaDTO;
import br.com.doctorplus.gerenciador.model.dtos.usuario.CadastrarUsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.services.UsuarioService;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@ApiController(path = "usuario", name = "Usuarios", description = "Api responsável pelas operações de usuários")
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioService service;

    @Override
    public ResponseEntity<ResponseSucesso> cadastrar(CadastrarUsuarioDTO cadastrarUsuarioDTO) {
        return new ResponseEntity<>(service.cadastrar(cadastrarUsuarioDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return new ResponseEntity<>(service.buscar(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseSucesso> trocarSenha(SenhaDTO senha) {
        return new ResponseEntity<>(service.trocarSenha(senha), HttpStatus.ACCEPTED);
    }
}
