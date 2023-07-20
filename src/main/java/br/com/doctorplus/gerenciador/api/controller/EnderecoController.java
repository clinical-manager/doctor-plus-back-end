package br.com.doctorplus.gerenciador.api.controller;

import br.com.doctorplus.gerenciador.api.annotations.ApiController;
import br.com.doctorplus.gerenciador.api.annotations.SpringDocApiErrorResponseDTO;
import br.com.doctorplus.gerenciador.model.dtos.endereco.VisualizarEnderecoDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@ApiController(path = "endereco", name = "Enderecos", description = "Api responsável pelas operações que envolvem endereços")
public interface EnderecoController {

    @GetMapping("/{cep}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VisualizarEnderecoDTO.class))})})
    @SpringDocApiErrorResponseDTO(summary = "Buscar um endereco pelo cep")
    ResponseEntity<VisualizarEnderecoDTO> buscarPeloCep(@PathVariable("cep") String cep);

}
