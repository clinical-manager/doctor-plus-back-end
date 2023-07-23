package br.com.doctorplus.gerenciador.config.security;

import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.security.UsuarioAutenticacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String email;
    private final Long idOrganizacao;
    private final String nome;
    private final String userName;
    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public static UserDetailsImpl build(UsuarioAutenticacao usuario) {
        List<GrantedAuthority> authorityList = Stream.of(usuario.papel()).map(role -> new SimpleGrantedAuthority((role.getAuthority())))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                usuario.id(),
                usuario.email(),
                usuario.idOrganizacao(),
                usuario.nome(),
                usuario.email(),
                usuario.password(),
                authorityList
        );
    }

}
