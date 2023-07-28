package br.com.doctorplus.gerenciador.api.controller.impl;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.controller.ApisPublicaController;
import br.com.doctorplus.gerenciador.model.dtos.apispublica.VisualizarCnpjDTO;
import br.com.doctorplus.gerenciador.model.dtos.endereco.VisualizarEnderecoDTO;
import br.com.doctorplus.gerenciador.model.services.CnpjService;
import br.com.doctorplus.gerenciador.model.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ApiController(path = "apis-publica", name = "Enderecos", description = "Api responsável pelas operações que envolvem endereços")
@RequiredArgsConstructor
public class ApisPublicaControllerImpl implements ApisPublicaController {

    private final EnderecoService service;
    private final CnpjService cnpjService;

    @Override
    public ResponseEntity<VisualizarEnderecoDTO> buscarPeloCep(String cep) {
        return new ResponseEntity<>(service.buscarPeloCep(cep), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VisualizarCnpjDTO> buscarPeloCnpj(String cnpj) {
        return new ResponseEntity<>(cnpjService.buscarPeloCnpj(cnpj), HttpStatus.OK);
    }
}
