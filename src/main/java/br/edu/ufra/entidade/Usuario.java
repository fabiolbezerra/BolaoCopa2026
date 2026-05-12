package br.edu.ufra.entidade;

import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usuario")
@NamedQueries({
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
        @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 45)
    private String sobrenome;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "perfil", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private EnumPerfilUsuario perfil;

    @Column(name = "tipo", nullable = false, length = 10)
    private String tipo;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campus", nullable = false)
    private Campus campus;

    @OneToMany(mappedBy = "dono")
    private Set<Bolao> boloes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Palpite> palpites = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Participante> participantes = new LinkedHashSet<>();

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumPerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(EnumPerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Set<Bolao> getBoloes() {
        return boloes;
    }

    public void setBoloes(Set<Bolao> boloes) {
        this.boloes = boloes;
    }

    public Set<Palpite> getPalpites() {
        return palpites;
    }

    public void setPalpites(Set<Palpite> palpites) {
        this.palpites = palpites;
    }

    public Set<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Set<Participante> participantes) {
        this.participantes = participantes;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;

        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}