package br.com.doctorplus.gerenciador.config.security;

import br.com.doctorplus.gerenciador.model.mapper.UsuarioMapper;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.security.UsuarioAutenticacao;
import br.com.doctorplus.gerenciador.model.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioService usuarioService;

    private final UsuarioMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarUsuarioPorUserName(username);
        UsuarioAutenticacao usuarioAutenticacao = mapper.mapperAutenticado(usuario);
        //UsuarioAutenticacao usuarioAutenticacao = new UsuarioAutenticacao(usuario.getId(), usuario.getOrganizacao().getId(), usuario.getNome(), usuario.getEmail(), usuario.getFuncionalidade())
        return UserDetailsImpl.build(usuarioAutenticacao);
    }
}