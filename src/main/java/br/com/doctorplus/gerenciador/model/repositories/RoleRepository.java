package br.com.doctorplus.gerenciador.model.repositories;

import br.com.doctorplus.gerenciador.model.entities.Role;
import br.com.doctorplus.gerenciador.model.enums.FuncionalidadesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByTipo(FuncionalidadesEnum tipo);
}