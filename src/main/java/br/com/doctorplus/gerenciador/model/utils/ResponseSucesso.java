package br.com.doctorplus.gerenciador.model.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseSucesso(
        String mensagem,
        Long id
) {
}
