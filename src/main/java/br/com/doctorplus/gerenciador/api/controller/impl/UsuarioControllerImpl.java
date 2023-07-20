package br.com.doctorplus.gerenciador.api.controller.impl;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.controller.UsuarioController;
import br.com.doctorplus.gerenciador.model.dtos.usuario.CadastrarUsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.services.UsuarioService;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
}
