package br.com.doctorplus.gerenciador.model.repositories;

import br.com.doctorplus.gerenciador.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByDescricao(String descricao);
}