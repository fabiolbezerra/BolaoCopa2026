package br.edu.ufra.entidade;

import javax.persistence.*;

@Entity
@Table(name = "participante")
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

}