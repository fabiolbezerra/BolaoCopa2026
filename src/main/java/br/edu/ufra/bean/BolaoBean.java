package br.edu.ufra.bean;

import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Participante;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.BolaoRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class BolaoBean {

    private final BolaoRN BOLAO_RN = new BolaoRN();

    private Bolao bolao = new Bolao();

    private List<Bolao> boloes;
    private List<Participante> participantes;

    public BolaoBean() {
    }

    @PostConstruct
    public void init() {
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();
        if (usuarioLogado != null && usuarioLogado.getPerfil().isAdministrador()) {
            boloes = BOLAO_RN.listarTodos();
        } else {
            boloes = BOLAO_RN.obter(usuarioLogado);
        }
    }

    public Bolao getBolao() {
        return bolao;
    }

    public void setBolao(Bolao bolao) {
        this.bolao = BOLAO_RN.obter(bolao.getId());
        this.participantes = this.bolao.getParticipantes();
    }

    public List<Bolao> getBoloes() {
        return boloes;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void excluir() {
        //TODO: implementar a lógica para EXCLUSÃO do bolão
    }

    public void salvar() {
        //TODO: implementar a lógica para SALVAR o bolão
    }

    public boolean isApenasParticipante(Bolao bolao) {
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();
        if (bolao == null || usuarioLogado == null) {
            return false;
        }
        if (bolao.getDono().equals(usuarioLogado)) {
            return false;
        }
        for (Participante p : bolao.getParticipantes()) {
            if (p.getUsuario().equals(usuarioLogado)) {
                return true;
            }
        }
        return false;
    }

    public void sair() {
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();
        if (BOLAO_RN.sairDoBolao(bolao, usuarioLogado)) {
            UtilBean.criarMensagemDeInformacao("Você saiu do bolão");
            boloes = BOLAO_RN.obter(usuarioLogado);
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível sair do bolão");
        }
    }
}
