package br.com.doctorplus.gerenciador.model.exception;

import lombok.NonNull;

import java.time.OffsetDateTime;

public record ResponseError(
        @NonNull String mensagem, @NonNull OffsetDateTime timestamp, int statusCode
) {
}
