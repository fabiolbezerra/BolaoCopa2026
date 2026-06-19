package br.edu.ufra.bean;

import br.edu.ufra.entidade.Grupo;
import br.edu.ufra.entidade.Selecao;
import br.edu.ufra.rn.GrupoRN;
import br.edu.ufra.rn.SelecaoRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class SelecaoBean {

    private List<Selecao> selecoes;
    private List<Grupo> grupos;

    private Selecao selecao = new Selecao();

    private final SelecaoRN SELECAO_RN = new SelecaoRN();
    private final GrupoRN GRUPO_RN = new GrupoRN();

    public SelecaoBean() {
    }

    @PostConstruct
    public void init() {
        selecoes = SELECAO_RN.listarTodos();
        grupos = GRUPO_RN.listarTodos();
    }

    public List<Selecao> getSelecoes() {
        return selecoes;
    }

    public void setSelecoes(List<Selecao> selecoes) {
        this.selecoes = selecoes;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    public void salvar() {
        if (SELECAO_RN.salvar(selecao)) {
            UtilBean.criarMensagemDeInformacao("Seleção salva");
            selecao = new Selecao();
            selecoes = SELECAO_RN.listarTodos();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível salvar a seleção");
        }
    }

    public void excluir() {
        if (SELECAO_RN.excluir(selecao)) {
            UtilBean.criarMensagemDeInformacao("Seleção excluída");
            selecoes = SELECAO_RN.listarTodos();
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível excluir a seleção");
        }
    }
}