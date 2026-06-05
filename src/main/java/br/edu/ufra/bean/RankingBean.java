package br.edu.ufra.bean;

import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.CampusRN;
import br.edu.ufra.rn.CursoRN;
import br.edu.ufra.rn.UsuarioRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class RankingBean {

    private List<Usuario> rankingGeral;
    private List<Usuario> rankingCampus;
    private List<Usuario> rankingCurso;

    private final UsuarioRN USUARIO_RN = new UsuarioRN();
    private final CampusRN CAMPUS_RN = new CampusRN();
    private final CursoRN CURSO_RN = new CursoRN();

    public RankingBean() {
    }

    @PostConstruct
    public void init() {
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();

        rankingGeral = USUARIO_RN.obterOrdenadoPorPontuacao();

        if (usuarioLogado != null && usuarioLogado.getCampus() != null) {
            rankingCampus = USUARIO_RN.obterOrdenadoPorPontuacao(usuarioLogado.getCampus());
        }

        if (usuarioLogado != null && usuarioLogado.getCurso() != null) {
            rankingCurso = USUARIO_RN.obterOrdenadoPorPontuacao(usuarioLogado.getCurso());
        }
    }

    public List<Usuario> getRankingGeral() {
        return rankingGeral;
    }

    public List<Usuario> getRankingCampus() {
        return rankingCampus;
    }

    public List<Usuario> getRankingCurso() {
        return rankingCurso;
    }
}