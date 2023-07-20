package br.com.doctorplus.gerenciador.model.services;

import br.com.doctorplus.gerenciador.model.dtos.usuario.CadastrarUsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Organizacao;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizacaoService {
    public void mapearOrganizacao(CadastrarUsuarioDTO cadastrarUsuarioDTO, Usuario usuario) {
        Organizacao organizacao = new Organizacao();
        organizacao.setNome(cadastrarUsuarioDTO.nome());
        usuario.setOrganizacao(organizacao);
    }
}
