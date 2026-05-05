package br.edu.ufra.entidade;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bolao")
public class Bolao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "criacao", nullable = false)
    private Instant criacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dono", nullable = false)
    private br.edu.ufra.entidade.Usuario dono;

    @OneToMany(mappedBy = "bolao")
    private Set<br.edu.ufra.entidade.Participante> participantes = new LinkedHashSet<>();

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

    public Instant getCriacao() {
        return criacao;
    }

    public void setCriacao(Instant criacao) {
        this.criacao = criacao;
    }

    public br.edu.ufra.entidade.Usuario getDono() {
        return dono;
    }

    public void setDono(br.edu.ufra.entidade.Usuario dono) {
        this.dono = dono;
    }

    public Set<br.edu.ufra.entidade.Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Set<br.edu.ufra.entidade.Participante> participantes) {
        this.participantes = participantes;
    }

}