package br.edu.ufra.bean;

import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Curso;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.CampusRN;
import br.edu.ufra.rn.CursoRN;
import br.edu.ufra.rn.UsuarioRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class RankingBean {

    private List<Usuario> rankingGeral;
    private List<Usuario> rankingCampus;
    private List<Usuario> rankingCurso;

    private Campus campus;
    private Curso curso;

    private final UsuarioRN USUARIO_RN = new UsuarioRN();
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

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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

    public void atualizarRankingCampus() {
        rankingCampus = USUARIO_RN.obterOrdenadoPorPontuacao(campus);
        if (rankingCampus != null) {
            UtilBean.criarMensagemDeInformacao("Ranking campus atualizado com sucesso!");
        } else {
            UtilBean.criarMensagemDeAviso("Nenhuma lista foi encontrada!");
        }
    }

    public void atualizarRankingCurso() {
        rankingCurso = USUARIO_RN.obterOrdenadoPorPontuacao(curso);
        if (rankingCurso != null) {
            UtilBean.criarMensagemDeInformacao("Ranking curso atualizado com sucesso!");
        } else {
            UtilBean.criarMensagemDeAviso("Nenhuma lista foi encontrada!");
        }
    }

}