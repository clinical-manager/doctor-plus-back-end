package br.com.doctorplus.gerenciador.model.security;

import br.com.doctorplus.gerenciador.model.entities.Funcionalidade;

public record UsuarioAutenticacao(
        Long id,
        Long idOrganizacao,
        String nome,
        String email,
        Funcionalidade papel
) {
}
