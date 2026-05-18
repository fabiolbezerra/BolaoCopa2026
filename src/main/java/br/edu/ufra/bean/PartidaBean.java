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
    private List<Partida> partidas;
    private Partida partida = new Partida();
    private final PartidaRN PARTIDA_RN = new PartidaRN();

    public PartidaBean() {
        System.out.println("Criando PartidaBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("PartidaBean.init()");
        this.partidas = this.PARTIDA_RN.listarTodos();
    }

    public List<Partida> getPartidas() {
        return this.partidas;
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
            this.partidas = this.PARTIDA_RN.listarTodos();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível excluir a partida");
        }
    }

    public void salvar() {
        if (this.PARTIDA_RN.salvar(this.partida)) {
            UtilBean.criarMensagemDeInformacao("Partida salva");
            partidas = PARTIDA_RN.listarTodos();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível salvar a partida");
        }
    }

}