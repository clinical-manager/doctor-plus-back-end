package br.com.doctorplus.gerenciador.model.repositories;

import br.com.doctorplus.gerenciador.model.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}