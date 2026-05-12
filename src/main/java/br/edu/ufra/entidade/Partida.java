package br.edu.ufra.entidade;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "partida")
@NamedQueries({
        @NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p"),
        @NamedQuery(name = "Partida.findById", query = "SELECT p FROM Partida p WHERE p.id = :id")})
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "inicioDaPartida", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioDaPartida;

    @Column(name = "atualizacaoDoResultado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date atualizacaoDoResultado;

    @Column(name = "local", nullable = false, length = 45)
    private String local;

    @Column(name = "golsA")
    private Integer golsA;

    @Column(name = "golsB")
    private Integer golsB;

    @Column(name = "tipoDeVitoria", length = 20)
    private String tipoDeVitoria;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fase", nullable = false)
    private Fase fase;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "selecaoA", nullable = false)
    private br.edu.ufra.entidade.Selecao selecaoA;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "selecaoB", nullable = false)
    private br.edu.ufra.entidade.Selecao selecaoB;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vencedor")
    private br.edu.ufra.entidade.Selecao vencedor;

    @OneToMany(mappedBy = "partida")
    private Set<Palpite> palpites = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInicioDaPartida() {
        return inicioDaPartida;
    }

    public void setInicioDaPartida(Date inicioDaPartida) {
        this.inicioDaPartida = inicioDaPartida;
    }

    public Date getAtualizacaoDoResultado() {
        return atualizacaoDoResultado;
    }

    public void setAtualizacaoDoResultado(Date atualizacaoDoResultado) {
        this.atualizacaoDoResultado = atualizacaoDoResultado;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getGolsA() {
        return golsA;
    }

    public void setGolsA(Integer golsA) {
        this.golsA = golsA;
    }

    public Integer getGolsB() {
        return golsB;
    }

    public void setGolsB(Integer golsB) {
        this.golsB = golsB;
    }

    public String getTipoDeVitoria() {
        return tipoDeVitoria;
    }

    public void setTipoDeVitoria(String tipoDeVitoria) {
        this.tipoDeVitoria = tipoDeVitoria;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public br.edu.ufra.entidade.Selecao getSelecaoA() {
        return selecaoA;
    }

    public void setSelecaoA(br.edu.ufra.entidade.Selecao selecaoA) {
        this.selecaoA = selecaoA;
    }

    public br.edu.ufra.entidade.Selecao getSelecaoB() {
        return selecaoB;
    }

    public void setSelecaoB(br.edu.ufra.entidade.Selecao selecaoB) {
        this.selecaoB = selecaoB;
    }

    public br.edu.ufra.entidade.Selecao getVencedor() {
        return vencedor;
    }

    public void setVencedor(br.edu.ufra.entidade.Selecao vencedor) {
        this.vencedor = vencedor;
    }

    public Set<Palpite> getPalpites() {
        return palpites;
    }

    public void setPalpites(Set<Palpite> palpites) {
        this.palpites = palpites;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Partida partida)) return false;

        return Objects.equals(id, partida.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}