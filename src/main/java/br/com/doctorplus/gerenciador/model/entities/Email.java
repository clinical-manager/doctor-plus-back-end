package br.com.doctorplus.gerenciador.model.entities;

public record Email(
        String from,
        String to,
        String subject,
        String text
) {
}
