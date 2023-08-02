package br.com.doctorplus.gerenciador.api.controller;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.annotations.SpringDocApiErrorResponseDTO;
import br.com.doctorplus.gerenciador.model.dtos.apispublica.VisualizarCnpjDTO;
import br.com.doctorplus.gerenciador.model.dtos.endereco.VisualizarEnderecoDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@ApiController(path = "apis-publica", name = "Apis Publica", description = "Api responsável por integrações com apis publicas")
public interface ApisPublicaController {

    @GetMapping("/cep/{cep}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VisualizarEnderecoDTO.class))})})
    @SpringDocApiErrorResponseDTO(summary = "Buscar um endereco pelo cep")
    ResponseEntity<VisualizarEnderecoDTO> buscarPeloCep(@PathVariable("cep") String cep);

    @GetMapping("/cnpj/{cnpj}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VisualizarCnpjDTO.class))})})
    @SpringDocApiErrorResponseDTO(summary = "Buscar um cnpj via api publica")
    ResponseEntity<VisualizarCnpjDTO> buscarPeloCnpj(@PathVariable("cnpj") String cnpj);

}
