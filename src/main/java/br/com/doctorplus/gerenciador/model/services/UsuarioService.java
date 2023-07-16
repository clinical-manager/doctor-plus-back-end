package br.com.doctorplus.gerenciador.model.services;


import br.com.doctorplus.gerenciador.model.UsuarioMapper;
import br.com.doctorplus.gerenciador.model.dtos.usuario.UsuarioDTO;
import br.com.doctorplus.gerenciador.model.entities.Organizacao;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.exception.NegocioException;
import br.com.doctorplus.gerenciador.model.exception.NotFoundException;
import br.com.doctorplus.gerenciador.model.helper.PasswordEncoderHelper;
import br.com.doctorplus.gerenciador.model.repositories.UsuarioRepository;
import br.com.doctorplus.gerenciador.model.utils.ResponseSucesso;
import br.com.doctorplus.gerenciador.model.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoderHelper passwordEncoderHelper;
    private final UsuarioMapper mapper;

    private final ResponseUtil response;

    public Usuario buscarUsuarioPorUserName(String username) {
        return repository.findByEmail(username).orElseThrow(
                () -> new NotFoundException("usuario.nao.encontrado")
        );
    }

    public ResponseSucesso cadastrar(UsuarioDTO usuarioDTO) {
        verificaSeUsuarioJaExiste(usuarioDTO.email());
        Usuario usuario = mapper.toUsuario(usuarioDTO);
        usuario.setSenha(passwordEncoderHelper.criptografarSenha(usuarioDTO.senha()));
        Organizacao organizacao = new Organizacao();
        organizacao.setNome(usuarioDTO.nome());
        usuario.setOrganizacao(organizacao);
        repository.save(usuario);
        return response.buildResponse("usuario.criado.sucesso", null);
    }

    private void verificaSeUsuarioJaExiste(String email) {
        if(repository.existsByEmailIgnoreCase(email)) {
            throw new NegocioException("usuario.existe");
        }
    }
}
