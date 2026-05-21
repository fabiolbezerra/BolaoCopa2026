package br.edu.ufra.bean;

import br.edu.ufra.entidade.Partida;
import br.edu.ufra.rn.PartidaRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class PartidaBean {
    private final PartidaRN PARTIDA_RN = new PartidaRN();

    private List<Partida> proximasPartidas;
    private List<Partida> partidasAnteriores;

    private Partida partida = new Partida();

    public PartidaBean() {
        System.out.println("Criando PartidaBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("PartidaBean.init()");
        this.proximasPartidas = this.PARTIDA_RN.obterProximasPartidas();
        this.proximasPartidas = this.PARTIDA_RN.obterPartidasAnteriores();
    }

    public List<Partida> getProximasPartidas() {
        return this.proximasPartidas;
    }

    public Partida getPartida() {
        return this.partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void publicar() {
        //TODO: Pra ti Paulo
    }

    public void processar() {
        //TODO: Pra ti Fábio
    }

    public void excluir() {
        if (this.PARTIDA_RN.excluir(this.partida)) {
            UtilBean.criarMensagemDeInformacao("Partida excluída");
            this.proximasPartidas = this.PARTIDA_RN.listarTodos();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível excluir a partida");
        }
    }

    public void salvar() {
        if (this.PARTIDA_RN.salvar(this.partida)) {
            UtilBean.criarMensagemDeInformacao("Partida salva");
            proximasPartidas = PARTIDA_RN.listarTodos();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível salvar a partida");
        }
    }

}