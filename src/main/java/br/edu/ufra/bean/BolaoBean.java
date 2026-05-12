package br.edu.ufra.bean;

import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.BolaoRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class BolaoBean {

    private final BolaoRN BOLAO_RN = new BolaoRN();
    private List<Bolao> boloes;
    private Bolao bolao = new Bolao();

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

    public List<Bolao> getBoloes() {
        return boloes;
    }

    public Bolao getBolao() {
        return bolao;
    }

    public void setBolao(Bolao bolao) {
        this.bolao = bolao;
    }

}
