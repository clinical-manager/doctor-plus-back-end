package br.com.doctorplus.gerenciador.api.controller.impl;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.controller.EnderecoController;
import br.com.doctorplus.gerenciador.model.dtos.endereco.VisualizarEnderecoDTO;
import br.com.doctorplus.gerenciador.model.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ApiController(path = "endereco", name = "Enderecos", description = "Api responsável pelas operações que envolvem endereços")
@RequiredArgsConstructor
public class EnderecoControllerImpl implements EnderecoController {

    private final EnderecoService service;


    @Override
    public ResponseEntity<VisualizarEnderecoDTO> buscarPeloCep(String cep) {
        return new ResponseEntity<>(service.buscarPeloCep(cep), HttpStatus.OK);
    }
}
