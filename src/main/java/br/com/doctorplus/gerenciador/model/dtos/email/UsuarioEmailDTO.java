package br.com.doctorplus.gerenciador.model.dtos.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEmailDTO {
        private String email;
        private String nome;
        private String codigoVerificacao;
        private String host;
        private String jwt;
}
