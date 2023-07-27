package br.com.doctorplus.gerenciador.model.repositories;

import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailAndStatus(String email, StatusEnum status);
    Optional<Usuario> findByEmailAndCodigoVerificacao(String email, String codigoVerificacao);
    boolean existsByEmailIgnoreCase(String email);
    Optional<Usuario> findByEmail(String username);

}