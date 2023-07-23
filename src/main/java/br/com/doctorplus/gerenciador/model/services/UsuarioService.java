package br.com.doctorplus.gerenciador.model.services;


import br.com.doctorplus.gerenciador.model.mapper.UsuarioMapper;
import br.com.doctorplus.gerenciador.model.dtos.usuario.CadastrarUsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.exception.NegocioException;
import br.com.doctorplus.gerenciador.model.exception.NotFoundException;
import br.com.doctorplus.gerenciador.model.repositories.UsuarioRepository;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import br.com.doctorplus.gerenciador.model.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper mapper;
    private final ResponseUtil response;
    private final EnderecoService enderecoService;
    private final OrganizacaoService organizacaoService;
    private final RoleService roleService;

    public Usuario buscarUsuarioPorUserName(String username) {
        return repository.findByEmail(username).orElseThrow(
                () -> new NotFoundException("usuario.nao.encontrado")
        );
    }

    public ResponseSucesso cadastrar(CadastrarUsuarioDTO cadastrarUsuarioDTO) {
        verificaSeUsuarioJaExiste(cadastrarUsuarioDTO.email());
        Usuario usuario = mapper.toUsuario(cadastrarUsuarioDTO);
        usuario.setPassword(passwordEncoder.encode(cadastrarUsuarioDTO.senha()));
        organizacaoService.mapearOrganizacao(cadastrarUsuarioDTO, usuario);
        enderecoService.mapearEndereco(cadastrarUsuarioDTO.endereco(), usuario);
        roleService.mapearRole(usuario);
        repository.save(usuario);
        return response.buildResponse("usuario.criado.sucesso", null);
    }

    private void verificaSeUsuarioJaExiste(String email) {
        if (repository.existsByEmailIgnoreCase(email)) {
            throw new NegocioException("usuario.existe");
        }
    }

    public List<Usuario> buscar() {
        return repository.findAll();
    }
}
