package br.edu.ufra.bean;

import br.edu.ufra.entidade.Partida;
import br.edu.ufra.rn.PartidaRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class PartidaBean {
    private List<Partida> partidas;
    private Partida partida = new Partida();
    private final PartidaRN PARTIDA_RN = new PartidaRN();

    public PartidaBean() {

    }

    public void init() {
        this.partidas = this.PARTIDA_RN.listar();
    }

    public List<Partida> getPartidas() {
        return this.partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public Partida getPartida() {
        return this.partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void excluir() {
        if (this.PARTIDA_RN.excluir(this.partida)) {
            UtilBean.criarMensagemDeInformacao("Partida excluída");
            this.partidas = this.PARTIDA_RN.listar();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível excluir a raça");
        }
    }

    public void salvar() {
        if (this.PARTIDA_RN.salvar(this.partida)) {
            UtilBean.criarMensagemDeInformacao("Partida salva");
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível salvar o raça");
        }

    }

}