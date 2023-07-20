package br.com.doctorplus.gerenciador.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexoEnum {

    MASCULINO('M'), FEMININO('F');

    private char nome;
}
