package br.com.doctorplus.gerenciador.model.mapper;


import br.com.doctorplus.gerenciador.model.dtos.usuario.CadastrarUsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.security.UsuarioAutenticacao;
import br.com.doctorplus.gerenciador.model.utils.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = MapperUtil.class)
public interface UsuarioMapper {


    @Mapping(target = "idOrganizacao", source = "organizacao.id")
    @Mapping(target = "papel", source = "role")
    UsuarioAutenticacao mapperAutenticado(Usuario usuario);

    Usuario toUsuario(CadastrarUsuarioDTO cadastrarUsuarioDTO);
}
