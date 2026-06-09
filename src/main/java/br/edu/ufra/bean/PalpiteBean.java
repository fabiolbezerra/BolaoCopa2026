package br.edu.ufra.bean;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.tipo.EnumTipoVitoria;
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
    private Integer selecaoVencedoraId;

    private List<Palpite> meusPalpites;
    private List<Palpite> palpitesDaPartida;

    private final PalpiteRN PALPITE_RN = new PalpiteRN();
    private final PartidaRN PARTIDA_RN = new PartidaRN();

    public PalpiteBean() {
    }

    @PostConstruct
    public void init() {
        String partidaID = UtilBean.obterValor("partidaID");
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();
        if (partidaID != null) {
            try {
                int id = Integer.parseInt(partidaID);
                partida = PARTIDA_RN.obter(id);
                if (partida == null) {
                    partida = new Partida();
                } else {
                    // Verifica se o palpiteiro já tem palpite nessa partida
                    Palpite palpiteExistente = PALPITE_RN.obter(usuarioLogado, partida);
                    if (palpiteExistente != null) {
                        palpite = palpiteExistente; // carrega para edição
                    } else {
                        palpite = new Palpite(); // cria novo
                        palpite.setUsuario(usuarioLogado);
                        palpite.setPartida(partida);
                    }
                }
            } catch (Exception e) {
                partida = new Partida();
                UtilBean.criarMensagemDeErro("Não foi possível recuperar a partida.");
            }
        }
        meusPalpites = PALPITE_RN.obter(usuarioLogado);
    }

    public void salvar() {
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();
        palpite.setUsuario(usuarioLogado);
        palpite.setPartida(partida);
        if (PALPITE_RN.salvar(palpite)) {
            UtilBean.criarMensagemDeInformacao("Palpite salvo com sucesso!");
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível salvar o palpite.");
            UtilBean.criarMensagemDeAviso("Limite", "Palpite pode ser definido até 30min antes do início da partida.");
        }
    }

    public void excluir() {
        if (PALPITE_RN.excluir(palpite)) {
            UtilBean.criarMensagemDeInformacao("Palpite excluído!");
            meusPalpites = PALPITE_RN.obter(UtilBean.obterUsuarioLogado());
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível excluir o palpite.");
        }
    }

    public EnumTipoVitoria[] getTiposDeVitoria() {
        return EnumTipoVitoria.values();
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

    public Integer getSelecaoVencedoraId() {
        return selecaoVencedoraId;
    }

    public void setSelecaoVencedoraId(Integer selecaoVencedoraId) {
        this.selecaoVencedoraId = selecaoVencedoraId;
    }

    public List<Palpite> getPalpitesDaPartida() {
        if (palpitesDaPartida == null) {
            palpitesDaPartida = PALPITE_RN.obter(partida);
        }
        return palpitesDaPartida;
    }
}
