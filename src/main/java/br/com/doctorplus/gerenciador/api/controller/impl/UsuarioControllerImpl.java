package br.com.doctorplus.gerenciador.api.controller.impl;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.controller.UsuarioController;
import br.com.doctorplus.gerenciador.model.dtos.usuario.UsuarioDTO;
import br.com.doctorplus.gerenciador.model.services.UsuarioService;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import br.com.doctorplus.gerenciador.model.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ApiController(path = "usuarios", name = "Usuarios", description = "Api responsável pelas operações de usuários")
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioService service;

    @Override
    public ResponseEntity<ResponseSucesso> cadastrar(UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(service.cadastrar(usuarioDTO), HttpStatus.CREATED);
    }
}
