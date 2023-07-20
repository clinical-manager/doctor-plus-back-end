package br.com.doctorplus.gerenciador.model.dtos.endereco;

public record EnderecoDTO(
        String cep,
        String numero,
        String complemento
) {
}
