package br.com.doctorplus.gerenciador.model.entities;


import br.com.doctorplus.gerenciador.model.enums.FuncionalidadesEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private FuncionalidadesEnum tipo;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.tipo.name();
    }
}

