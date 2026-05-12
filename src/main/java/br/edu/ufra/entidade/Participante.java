package br.edu.ufra.entidade;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "participante")
@NamedQueries({
        @NamedQuery(name = "Participante.findAll", query = "SELECT p FROM Participante p")
        , @NamedQuery(name = "Participante.findById", query = "SELECT p FROM Participante p WHERE p.id = :id")})
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bolao", nullable = false)
    private Bolao bolao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario", nullable = false)
    private br.edu.ufra.entidade.Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bolao getBolao() {
        return bolao;
    }

    public void setBolao(Bolao bolao) {
        this.bolao = bolao;
    }

    public br.edu.ufra.entidade.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(br.edu.ufra.entidade.Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}