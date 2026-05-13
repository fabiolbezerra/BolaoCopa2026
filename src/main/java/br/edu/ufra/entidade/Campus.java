package br.edu.ufra.entidade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "campus")
@NamedQueries({
        @NamedQuery(name = "Campus.findAll", query = "SELECT c FROM Campus c"),
        @NamedQuery(name = "Campus.findById", query = "SELECT c FROM Campus c WHERE c.id = :id")
})
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @OneToMany(mappedBy = "campus")
    private List<Curso> cursos = new ArrayList<>();

    @OneToMany(mappedBy = "campus")
    private List<Usuario> usuarios = new ArrayList<>();

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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Campus campus)) return false;

        return Objects.equals(id, campus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}