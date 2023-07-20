package br.com.doctorplus.gerenciador.model.dtos.usuario;

import br.com.doctorplus.gerenciador.model.dtos.endereco.EnderecoDTO;
import br.com.doctorplus.gerenciador.model.enums.SexoEnum;

public record CadastrarUsuarioDTO(
        String cpf,
        String nome,
        String sobrenome,
        String telefone,

        SexoEnum sexo,
        EnderecoDTO endereco,
        String email,
        String senha
) {}
