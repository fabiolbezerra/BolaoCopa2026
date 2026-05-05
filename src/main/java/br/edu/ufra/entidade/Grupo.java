package br.edu.ufra.entidade;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @OneToMany(mappedBy = "grupo")
    private Set<br.edu.ufra.entidade.Selecao> selecoes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<br.edu.ufra.entidade.Selecao> getSelecoes() {
        return selecoes;
    }

    public void setSelecoes(Set<br.edu.ufra.entidade.Selecao> selecoes) {
        this.selecoes = selecoes;
    }

}