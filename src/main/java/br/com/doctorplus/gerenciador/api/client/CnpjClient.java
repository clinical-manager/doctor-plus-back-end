package br.com.doctorplus.gerenciador.api.client;

import br.com.doctorplus.gerenciador.model.dtos.apispublica.VisualizarCnpjDTO;
import br.com.doctorplus.gerenciador.model.dtos.endereco.VisualizarEnderecoDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "${doctor-plus.feign.client.cnpj.name}", url = "${doctor-plus.feign.client.cnpj.url}")
public interface CnpjClient {

    @GetMapping(value = "/buscarcnpj")
    @Headers({"Content-Type: text/html", "Accept: text/html"})
    String buscaPeloCnpj(@RequestParam(value = "cnpj") String cnpj);

}
