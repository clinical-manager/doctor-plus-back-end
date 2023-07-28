package br.com.doctorplus.gerenciador.model.services;

import br.com.doctorplus.gerenciador.api.client.CnpjClient;
import br.com.doctorplus.gerenciador.model.dtos.apispublica.VisualizarCnpjDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CnpjService {
    private final CnpjClient client;
    public VisualizarCnpjDTO buscarPeloCnpj(String cnpj) {
        JSONObject jsonObject = new JSONObject(client.buscaPeloCnpj(cnpj));
        String nomeFantasia = (String) jsonObject.get("NOME FANTASIA");
        String razaoSocial = (String) jsonObject.get("RAZAO SOCIAL");
        return new VisualizarCnpjDTO(nomeFantasia, razaoSocial, cnpj);
    }
}
