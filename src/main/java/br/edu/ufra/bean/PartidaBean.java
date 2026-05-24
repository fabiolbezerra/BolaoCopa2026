package br.edu.ufra.bean;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.rn.PalpiteRN;
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
    private final PalpiteRN PALPITE_RN = new PalpiteRN();

    private List<Partida> proximasPartidas;
    private List<Partida> partidasAnteriores;

    private Partida partida = new Partida();
    private Palpite palpite = new Palpite();

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

    public List<Partida> getPartidasAnteriores() {return this.partidasAnteriores;}

    public Partida getPartida() {
        return this.partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void publicar() {
        if(partida != null) {
            partida.setSelecaoA(partida.getSelecaoA());
            partida.setSelecaoB(partida.getSelecaoB());
            UtilBean.criarMensagemDeInformacao("Partida publicada com sucesso");
        } else {
            UtilBean.criarMensagemDeErro("Nãp foi possível publicar a partida");
        }
    }

    public void processar() {
        if (PARTIDA_RN.processar(partida)) {
            UtilBean.criarMensagemDeInformacao("Pontuação dos palpiteiros atualizada!");
            UtilBean.criarMensagemDeInformacao("Posição dos palpiteiros atualizada!");
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível atualizar pontuação e posição dos palpiteiros.");
            UtilBean.criarMensagemDeAviso("Uma partida deve corretamente atualizada, antes de ser processada.");
            UtilBean.criarMensagemDeAviso("Verifique os placares da partida.");
        }
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
    public void palpitar(){
        if (palpite != null) {
            palpite.setPartida(partida);
            if (PALPITE_RN.salvar()) {
                UtilBean.criarMensagemDeInformacao("Palpite registrado com sucesso");
                setPartida(PARTIDA_RN.obter(partida.getId()));
                palpite = new Palpite();
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível registrar o palpite");
            }
        }
    }

}