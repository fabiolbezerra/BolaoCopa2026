package br.edu.ufra.bean;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.PartidaRN;
import br.edu.ufra.rn.PalpiteRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class PalpiteBean {

    private Palpite palpite = new Palpite();
    private Partida partida = new Partida();
    private List<Palpite> meusPalpites;

    private final PalpiteRN PALPITE_RN = new PalpiteRN();
    private final PartidaRN PARTIDA_RN = new PartidaRN();

    public PalpiteBean() {
    }

    @PostConstruct
    public void init() {
        String partidaID = UtilBean.obterValor("partidaID");
        if (partidaID != null) {
            try {
                int id = Integer.parseInt(partidaID);
                partida = PARTIDA_RN.obter(id);
                if (partida == null) {
                    partida = new Partida();
                    UtilBean.criarMensagemDeAviso("Não foi possível recuperar a partida. Código inválido.");
                }
            } catch (Exception e) {
                partida = new Partida();
                UtilBean.criarMensagemDeErro("Não foi possível recuperar a partida.");
            }
        }
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();
        meusPalpites = PALPITE_RN.obter(usuarioLogado);
    }

    public void salvar() {
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();
        palpite.setUsuario(usuarioLogado);
        palpite.setPartida(partida);
        if (PALPITE_RN.salvar(palpite)) {
            UtilBean.criarMensagemDeInformacao("Palpite salvo!");
            meusPalpites = PALPITE_RN.obter(usuarioLogado);
            palpite = new Palpite();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível salvar o palpite.");
        }
    }

    public Palpite getPalpite() {
        return palpite;
    }

    public void setPalpite(Palpite palpite) {
        this.palpite = palpite;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public List<Palpite> getMeusPalpites() {
        return meusPalpites;
    }

    public void setMeusPalpites(List<Palpite> meusPalpites) {
        this.meusPalpites = meusPalpites;
    }
}