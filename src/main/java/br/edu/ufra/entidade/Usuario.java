package br.edu.ufra.entidade;

import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;
import br.edu.ufra.entidade.tipo.EnumTipoUsuario;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "dataCadastro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

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
    @Enumerated(EnumType.STRING)
    private EnumTipoUsuario tipo;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    @Column(name = "posicao")
    private Integer posicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campus", nullable = false)
    private Campus campus;

    @OneToMany(mappedBy = "dono")
    private List<Bolao> boloes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Palpite> palpites = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Participante> participantes = new ArrayList<>();

    @Column(name = "banido", nullable = false)
    private boolean banido;

    @Column(name = "justificativaBanimento", length = 150)
    private String justificativaBanimento;

    @Column(name = "dataBanimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataBanimento;

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

    public EnumTipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
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

    public List<Bolao> getBoloes() {
        return boloes;
    }

    public void setBoloes(List<Bolao> boloes) {
        this.boloes = boloes;
    }

    public List<Palpite> getPalpites() {
        return palpites;
    }

    public void setPalpites(List<Palpite> palpites) {
        this.palpites = palpites;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public boolean isBanido() {
        return banido;
    }

    public void setBanido(boolean banido) {
        this.banido = banido;
    }

    public String getJustificativaBanimento() {
        return justificativaBanimento;
    }

    public void setJustificativaBanimento(String justificativaBanimento) {
        this.justificativaBanimento = justificativaBanimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataBanimento() {
        return dataBanimento;
    }

    public void setDataBanimento(Date dataBanimento) {
        this.dataBanimento = dataBanimento;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(nome)
                .append(' ')
                .append(sobrenome)
                .append('(')
                .append((curso == null ? "" : curso.getNome() + "/"))
                .append(campus.getNome())
                .append(')');
        return sb.toString();
    }
}