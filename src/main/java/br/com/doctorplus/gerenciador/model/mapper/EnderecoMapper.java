package br.com.doctorplus.gerenciador.model.mapper;


import br.com.doctorplus.gerenciador.model.dtos.endereco.EnderecoDTO;
import br.com.doctorplus.gerenciador.model.entities.Endereco;
import org.mapstruct.Mapper;

@Mapper
public interface EnderecoMapper {

    Endereco toEndereco(EnderecoDTO enderecoDTO);
}
