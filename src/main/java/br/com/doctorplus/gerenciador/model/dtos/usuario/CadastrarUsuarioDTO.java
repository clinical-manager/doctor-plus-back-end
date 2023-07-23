package br.com.doctorplus.gerenciador.model.dtos.usuario;

import br.com.doctorplus.gerenciador.api.annotations.CaractereEspecialSenha;
import br.com.doctorplus.gerenciador.api.annotations.Cpf;
import br.com.doctorplus.gerenciador.model.dtos.endereco.EnderecoDTO;
import br.com.doctorplus.gerenciador.model.enums.SexoEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CadastrarUsuarioDTO(

        @NotBlank(message = "O campo cpf não pode ser vazio. Por favor, insira um valor no formato de cpf.")
        @Cpf(message = "O campo cpf é inválido. Por favor, insira um cpf válido.")
        String cpf,

        @NotBlank(message = "O campo nome não pode ser vazio. Por favor, insira um valor.")
        String nome,

        @NotBlank(message = "O campo sobrenome não pode ser vazio. Por favor, insira um valor.")
        String sobrenome,

        @NotBlank(message = "O campo telefone não pode ser vazio. Por favor, insira um valor.")
        String telefone,

        SexoEnum sexo,

        EnderecoDTO endereco,

        @Email(message = "O campo e-mail deve ser no formato de email, ex: exemplo@provedor.com")
        String email,

        @Size(min = 6, message = "O campo senha deve possuir no mínimo 8 caracteres.")
        @CaractereEspecialSenha(message = "No campo senha, é necessário possuir um caractere especial, um número e letras maiúsculas e minúsculas.")
        String senha
) {

}
