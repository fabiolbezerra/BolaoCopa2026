package br.edu.ufra.entidade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "fase")
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "eliminatoria", nullable = false)
    private Byte eliminatoria;

    @Column(name = "inicio", nullable = false)
    private LocalDate inicio;

    @Column(name = "fim", nullable = false)
    private LocalDate fim;

    @OneToMany(mappedBy = "fase")
    private Set<br.edu.ufra.entidade.Partida> partidas = new LinkedHashSet<>();

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

    public Byte getEliminatoria() {
        return eliminatoria;
    }

    public void setEliminatoria(Byte eliminatoria) {
        this.eliminatoria = eliminatoria;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public Set<br.edu.ufra.entidade.Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(Set<br.edu.ufra.entidade.Partida> partidas) {
        this.partidas = partidas;
    }

}