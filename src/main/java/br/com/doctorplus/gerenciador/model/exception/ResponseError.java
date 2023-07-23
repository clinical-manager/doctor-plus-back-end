package br.com.doctorplus.gerenciador.model.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NonNull;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include. NON_NULL)
public record ResponseError(
        String mensagem, @NonNull OffsetDateTime timestamp, int statusCode, List<String> mensagens
) {
}
