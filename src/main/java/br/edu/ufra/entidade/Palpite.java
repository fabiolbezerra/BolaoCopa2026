package br.edu.ufra.entidade;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "palpite")
@NamedQueries({
        @NamedQuery(name = "Palpite.findAll", query = "SELECT p FROM Palpite p"),
        @NamedQuery(name = "Palpite.findById", query = "SELECT p FROM Palpite p WHERE p.id = :id")})
public class Palpite {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dataDoPalpite", nullable = false)
    private Instant dataDoPalpite;

    @Column(name = "ultimaAlteracao")
    private Instant ultimaAlteracao;

    @Column(name = "golsA", nullable = false)
    private Integer golsA;

    @Column(name = "golsB", nullable = false)
    private Integer golsB;

    @Column(name = "tipoDeVitoria", nullable = false, length = 20)
    private String tipoDeVitoria;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario", nullable = false)
    private br.edu.ufra.entidade.Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partida", nullable = false)
    private br.edu.ufra.entidade.Partida partida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDataDoPalpite() {
        return dataDoPalpite;
    }

    public void setDataDoPalpite(Instant dataDoPalpite) {
        this.dataDoPalpite = dataDoPalpite;
    }

    public Instant getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Instant ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
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

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public br.edu.ufra.entidade.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(br.edu.ufra.entidade.Usuario usuario) {
        this.usuario = usuario;
    }

    public br.edu.ufra.entidade.Partida getPartida() {
        return partida;
    }

    public void setPartida(br.edu.ufra.entidade.Partida partida) {
        this.partida = partida;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Palpite))
			return false;
		Palpite other = (Palpite) obj;
		return Objects.equals(id, other.id);
	}

}