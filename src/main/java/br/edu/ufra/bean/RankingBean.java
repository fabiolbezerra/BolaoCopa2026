package br.edu.ufra.bean;

import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Curso;
import br.edu.ufra.entidade.Usuario;
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

    public RankingBean() {
    }

    @PostConstruct
    public void init() {
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();

        rankingGeral = USUARIO_RN.obterOrdenadosPorPontuacao(0); //todos

        if (usuarioLogado != null && usuarioLogado.getCampus() != null) {
            rankingCampus = USUARIO_RN.obterOrdenadosPorPontuacao(usuarioLogado.getCampus());
        }

        if (usuarioLogado != null && usuarioLogado.getCurso() != null) {
            rankingCurso = USUARIO_RN.obterOrdenadosPorPontuacao(usuarioLogado.getCurso());
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
        rankingCampus = USUARIO_RN.obterOrdenadosPorPontuacao(campus);
        if (rankingCampus != null) {
            UtilBean.criarMensagemDeInformacao("Ranking campus atualizado com sucesso!");
        } else {
            UtilBean.criarMensagemDeAviso("Nenhuma lista foi encontrada!");
        }
    }

    public void atualizarRankingCurso() {
        rankingCurso = USUARIO_RN.obterOrdenadosPorPontuacao(curso);
        if (rankingCurso != null) {
            UtilBean.criarMensagemDeInformacao("Ranking curso atualizado com sucesso!");
        } else {
            UtilBean.criarMensagemDeAviso("Nenhuma lista foi encontrada!");
        }
    }

    public void notificarRanking() {
        //Notificação do ranking aos melhores posicionados
        List<String> notificacoes = USUARIO_RN.notificarRanking();
        for (String notificacao : notificacoes) {
            UtilBean.criarMensagemDeInformacao("Notificação", notificacao);
        }
    }

}