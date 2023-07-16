package br.com.doctorplus.gerenciador.model;


import br.com.doctorplus.gerenciador.model.dtos.usuario.UsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.security.UsuarioAutenticacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UsuarioMapper {


    @Mapping(target = "idOrganizacao", source = "organizacao.id")
    UsuarioAutenticacao mapperAutenticado(Usuario usuario);

    Usuario toUsuario(UsuarioDTO usuarioDTO);
}
