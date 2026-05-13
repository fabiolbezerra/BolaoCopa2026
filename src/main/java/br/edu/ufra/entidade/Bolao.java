package br.edu.ufra.entidade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bolao")
@NamedQueries({
        @NamedQuery(name = "Bolao.findAll", query = "SELECT b FROM Bolao b")
        , @NamedQuery(name = "Bolao.findById", query = "SELECT b FROM Bolao b WHERE b.id = :id")})
public class Bolao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "criacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date criacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dono", nullable = false)
    private Usuario dono;

    @OneToMany(mappedBy = "bolao")
    private List<Participante> participantes = new ArrayList<>();

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

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Bolao bolao)) return false;

        return Objects.equals(id, bolao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}