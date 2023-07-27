package br.com.doctorplus.gerenciador.model.dtos.autenticacao;

public record VerificaCodigoDTO(
        String email,
        String codigoVerificacao
) {
}
