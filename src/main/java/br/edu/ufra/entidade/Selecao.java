package br.edu.ufra.entidade;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "selecao")
@NamedQueries({
        @NamedQuery(name = "Selecao.findAll", query = "SELECT s FROM Selecao s"),
        @NamedQuery(name = "Selecao.findById", query = "SELECT s FROM Selecao s WHERE s.id = :id")})
public class Selecao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "sigla", nullable = false, length = 3)
    private String sigla;

    @Column(name = "simbolo", length = 45)
    private String simbolo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grupo", nullable = false)
    private Grupo grupo;

    @OneToMany(mappedBy = "selecaoA")
    private Set<Partida> partidasSelecaoA = new LinkedHashSet<>();

    @OneToMany(mappedBy = "selecaoB")
    private Set<Partida> partidasSelecaoB = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vencedor")
    private Set<Partida> partidasVencedor = new LinkedHashSet<>();

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Set<Partida> getPartidasSelecaoA() {
        return partidasSelecaoA;
    }

    public void setPartidasSelecaoA(Set<Partida> partidasSelecaoA) {
        this.partidasSelecaoA = partidasSelecaoA;
    }

    public Set<Partida> getPartidasSelecaoB() {
        return partidasSelecaoB;
    }

    public void setPartidasSelecaoB(Set<Partida> partidasSelecaoB) {
        this.partidasSelecaoB = partidasSelecaoB;
    }

    public Set<Partida> getPartidasVencedor() {
        return partidasVencedor;
    }

    public void setPartidasVencedor(Set<Partida> partidasVencedor) {
        this.partidasVencedor = partidasVencedor;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Selecao selecao)) return false;

        return Objects.equals(id, selecao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}