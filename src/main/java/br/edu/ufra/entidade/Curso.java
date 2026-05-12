package br.edu.ufra.entidade;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "curso")
@NamedQueries({
        @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
        @NamedQuery(name = "Curso.findById", query = "SELECT c FROM Curso c WHERE c.id = :id")})
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "sigla", nullable = false, length = 10)
    private String sigla;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campus", nullable = false)
    private Campus campus;

    @OneToMany(mappedBy = "curso")
    private Set<br.edu.ufra.entidade.Usuario> usuarios = new LinkedHashSet<>();

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

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Set<br.edu.ufra.entidade.Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<br.edu.ufra.entidade.Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Curso))
            return false;
        Curso curso = (Curso) object;
        return Objects.equals(id, curso.id);

    }

}