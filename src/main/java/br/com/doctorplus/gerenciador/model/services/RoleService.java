package br.com.doctorplus.gerenciador.model.services;


import br.com.doctorplus.gerenciador.model.entities.Role;
import br.com.doctorplus.gerenciador.model.entities.Usuario;
import br.com.doctorplus.gerenciador.model.enums.FuncionalidadesEnum;
import br.com.doctorplus.gerenciador.model.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public void mapearRole(Usuario usuario) {
        Role role = repository.findByDescricao(FuncionalidadesEnum.ROLE_PROFISSIONAL_SAUDE.name());
        usuario.setRole(role);
    }
}
