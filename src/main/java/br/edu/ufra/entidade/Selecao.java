package br.edu.ufra.entidade;

import javax.persistence.*;
import java.util.*;

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
    private List<Partida> partidasSelecaoA = new ArrayList<>();

    @OneToMany(mappedBy = "selecaoB")
    private List<Partida> partidasSelecaoB = new ArrayList<>();

    @OneToMany(mappedBy = "vencedor")
    private List<Partida> partidasVencedor = new ArrayList<>();

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


    public List<Partida> getPartidasSelecaoA() {
        return partidasSelecaoA;
    }

    public void setPartidasSelecaoA(List<Partida> partidasSelecaoA) {
        this.partidasSelecaoA = partidasSelecaoA;
    }

    public List<Partida> getPartidasSelecaoB() {
        return partidasSelecaoB;
    }

    public void setPartidasSelecaoB(List<Partida> partidasSelecaoB) {
        this.partidasSelecaoB = partidasSelecaoB;
    }

    public List<Partida> getPartidasVencedor() {
        return partidasVencedor;
    }

    public void setPartidasVencedor(List<Partida> partidasVencedor) {
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