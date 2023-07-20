package br.com.doctorplus.gerenciador.model.services;

import br.com.doctorplus.gerenciador.api.client.ViaCepClient;
import br.com.doctorplus.gerenciador.model.dtos.endereco.EnderecoDTO;
import br.com.doctorplus.gerenciador.model.dtos.endereco.VisualizarEnderecoDTO;
import br.com.doctorplus.gerenciador.model.entities.Endereco;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.mapper.EnderecoMapper;
import br.com.doctorplus.gerenciador.model.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;
    private final ViaCepClient viaCepClient;
    public VisualizarEnderecoDTO buscarPeloCep(String cep) {
        return viaCepClient.buscaPeloCep(cep);
    }
    public void mapearEndereco(EnderecoDTO enderecoDTO, Usuario usuario) {
        Endereco endereco = mapper.toEndereco(enderecoDTO);
        usuario.setEndereco(endereco);
    }
}
