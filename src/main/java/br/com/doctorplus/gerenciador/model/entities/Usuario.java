package br.com.doctorplus.gerenciador.model.entities;

import br.com.doctorplus.gerenciador.model.enums.SexoEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobrenome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "SEXO")
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String password;

    @Column(name = "TELEFONE")
    private String telefone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ORGANIZACAO")
    private Organizacao organizacao;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "ID_ROLE")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ENDERECO")
    private Endereco endereco;

}
