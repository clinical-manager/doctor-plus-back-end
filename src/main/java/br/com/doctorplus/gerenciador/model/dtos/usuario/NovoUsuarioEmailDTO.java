package br.com.doctorplus.gerenciador.model.dtos.usuario;

public record NovoUsuarioEmailDTO(
        String nome,
        String email,
        int codigo
) {
}
