package br.com.doctorplus.gerenciador.api.client;

import br.com.doctorplus.gerenciador.model.dtos.endereco.VisualizarEnderecoDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${doctor-plus.feign.client.via-plus.name}", url = "${doctor-plus.feign.client.via-plus.url}")
public interface ViaCepClient {

    @GetMapping(value = "/{cep}/json")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    VisualizarEnderecoDTO buscaPeloCep(@PathVariable(value = "cep") String cep);

}
