package br.edu.ufra.bean;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.rn.PalpiteRN;
import br.edu.ufra.rn.PartidaRN;
import br.edu.ufra.util.UtilBean;
import br.edu.ufra.util.UtilData;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
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
        proximasEAnteriores();
    }

    private void proximasEAnteriores() {
        this.proximasPartidas = this.PARTIDA_RN.obterProximasPartidas();
        this.partidasAnteriores = this.PARTIDA_RN.obterPartidasAnteriores();
    }

    public boolean isExpirada(Partida partida) {
        Date agora = new Date();
        Date agoraMais30 = UtilData.adicionarMinutos(agora, 30);
        return agoraMais30.after(partida.getInicioDaPartida());
    }

    public List<Partida> getProximasPartidas() {
        return this.proximasPartidas;
    }

    public List<Partida> getPartidasAnteriores() {
        return this.partidasAnteriores;
    }

    public Partida getPartida() {
        return this.partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

        public void processar() {
        if (PARTIDA_RN.processarPontuacao(partida)) {
            UtilBean.criarMensagemDeInformacao("Pontuação dos palpiteiros atualizada!");
            if (PARTIDA_RN.processarOrdenacao(partida)) {
                UtilBean.criarMensagemDeInformacao("Posição dos palpiteiros atualizada!");
            }
            proximasEAnteriores();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível atualizar pontuação e posição dos palpiteiros.");
            UtilBean.criarMensagemDeAviso("Placar", "Verifique o placar (gols e tipo de vitória) da partida.");
        }
    }

    public void excluir() {
        if (this.PARTIDA_RN.excluir(this.partida)) {
            UtilBean.criarMensagemDeInformacao("Partida excluída");
            proximasEAnteriores();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível excluir a partida");
        }
    }

    public void publicar() {
        this.partida.setPublicada(true);
        if (this.PARTIDA_RN.salvar(this.partida)) {
            UtilBean.criarMensagemDeInformacao("Partida publicada");
            proximasEAnteriores();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível publicar a partida");
        }
    }

    public void salvar() {
        if (this.PARTIDA_RN.salvar(this.partida)) {
            UtilBean.criarMensagemDeInformacao("Partida salva");
            proximasEAnteriores();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível salvar a partida");
        }
    }

    public void palpitar() {
        if (palpite != null) {
            palpite.setPartida(partida);
            if (PALPITE_RN.salvar(palpite)) {
                UtilBean.criarMensagemDeInformacao("Palpite registrado com sucesso");
                setPartida(PARTIDA_RN.obter(partida.getId()));
                palpite = new Palpite();
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível registrar o palpite");
            }
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível registrar o palpite");
        }
    }

}