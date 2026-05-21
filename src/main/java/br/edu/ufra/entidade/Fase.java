package br.edu.ufra.entidade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fase")
@NamedQueries({
        @NamedQuery(name = "Fase.findAll", query = "SELECT f FROM Fase f"),
        @NamedQuery(name = "Fase.findById", query = "SELECT f FROM Fase f WHERE f.id = :id")})
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "eliminatoria", nullable = false)
    private boolean eliminatoria;

    @Column(name = "inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inicio;

    @Column(name = "fim", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fim;

    @OneToMany(mappedBy = "fase")
    private List<br.edu.ufra.entidade.Partida> partidas = new ArrayList<>();

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

    public boolean isEliminatoria() {
        return eliminatoria;
    }

    public void setEliminatoria(boolean eliminatoria) {
        this.eliminatoria = eliminatoria;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public List<br.edu.ufra.entidade.Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<br.edu.ufra.entidade.Partida> partidas) {
        this.partidas = partidas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Curso))
            return false;
        Fase fase = (Fase) object;
        return Objects.equals(id, fase.id);

    }

}