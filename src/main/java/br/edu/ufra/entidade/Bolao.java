package br.edu.ufra.entidade;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
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