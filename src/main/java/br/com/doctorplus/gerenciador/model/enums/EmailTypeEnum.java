package br.com.doctorplus.gerenciador.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTypeEnum {

    ESQUECI_SENHA("email-esqueci-senha.ftml"),
    CONFIRMAR_CADASTRO("email-confirmar-cadastro.ftl"),
    MUDAR_SENHA("email-mudar-senha.ftl");

    private String name;

}
