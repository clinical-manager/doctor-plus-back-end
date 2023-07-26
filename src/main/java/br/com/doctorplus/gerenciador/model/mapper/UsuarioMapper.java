package br.com.doctorplus.gerenciador.model.mapper;


import br.com.doctorplus.gerenciador.model.dtos.email.UsuarioEmailDTO;
import br.com.doctorplus.gerenciador.model.dtos.usuario.CadastrarUsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.enums.StatusEnum;
import br.com.doctorplus.gerenciador.model.security.UsuarioAutenticacao;
import br.com.doctorplus.gerenciador.model.utils.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = MapperUtil.class,  imports = {StatusEnum.class})
public interface UsuarioMapper {


    @Mapping(target = "idOrganizacao", source = "organizacao.id")
    @Mapping(target = "papel", source = "role")
    UsuarioAutenticacao mapperAutenticado(Usuario usuario);

    @Mapping(target = "status", expression = "java(StatusEnum.PENDENTE)")
    @Mapping(target = "codigoVerificacao", expression = "java( br.com.doctorplus.gerenciador.model.utils.Util.randomNumber().toString())")
    Usuario toUsuario(CadastrarUsuarioDTO cadastrarUsuarioDTO);

    UsuarioEmailDTO toUsuarioEmail(Usuario usuario);
}
