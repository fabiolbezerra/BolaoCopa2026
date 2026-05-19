package br.edu.ufra.bean;

import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Participante;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.rn.BolaoRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
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

        if (usuarioLogado != null
                && usuarioLogado.getPerfil().isAdministrador()) {

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

        if (this.bolao != null) {
            this.participantes = this.bolao.getParticipantes();
        }
    }

    public List<Bolao> getBoloes() {
        return boloes;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void salvar() {

        try {

            Usuario usuarioLogado = UtilBean.obterUsuarioLogado();

            if (usuarioLogado == null) {

                UtilBean.criarMensagemDeErro(
                        "Usuário não autenticado"
                );

                return;
            }

            // Apenas dono pode salvar
            if (bolao.getDono() != null
                    && !bolao.getDono().equals(usuarioLogado)) {

                UtilBean.criarMensagemDeErro(
                        "Apenas o dono pode salvar o bolão"
                );

                return;
            }

            // Define dono
            if (bolao.getDono() == null) {
                bolao.setDono(usuarioLogado);
            }

            // Define data de criação
            if (bolao.getCriacao() == null) {
                bolao.setCriacao(new Date());
            }

            boolean salvou = BOLAO_RN.salvar(bolao);

            if (salvou) {

                Participante participante = new Participante();

                participante.setUsuario(usuarioLogado);
                participante.setBolao(bolao);

                BOLAO_RN.adicionarParticipante(
                        bolao,
                        participante
                );

                boloes = BOLAO_RN.obter(usuarioLogado);

                UtilBean.criarMensagemDeInformacao(
                        "Bolão salvo com sucesso!"
                );

                bolao = new Bolao();

            } else {

                UtilBean.criarMensagemDeErro(
                        "Não foi possível salvar o bolão"
                );
            }

        } catch (Exception e) {

            UtilBean.criarMensagemDeErro(
                    "Erro ao salvar o bolão"
            );

            e.printStackTrace();
        }
    }

    public void excluir() {

        try {

            Usuario usuarioLogado = UtilBean.obterUsuarioLogado();

            if (usuarioLogado == null
                    || bolao == null
                    || bolao.getDono() == null) {

                UtilBean.criarMensagemDeErro(
                        "Não foi possível excluir o bolão"
                );

                return;
            }

            // Apenas dono pode excluir
            if (!bolao.getDono().equals(usuarioLogado)) {

                UtilBean.criarMensagemDeErro(
                        "Apenas o dono pode excluir o bolão"
                );

                return;
            }

            boolean excluiu = BOLAO_RN.excluir(bolao);

            if (excluiu) {

                boloes = BOLAO_RN.obter(usuarioLogado);

                UtilBean.criarMensagemDeInformacao(
                        "Bolão excluído com sucesso!"
                );

                bolao = new Bolao();

            } else {

                UtilBean.criarMensagemDeErro(
                        "Não foi possível excluir o bolão"
                );
            }

        } catch (Exception e) {

            UtilBean.criarMensagemDeErro(
                    "Erro ao excluir o bolão"
            );

            e.printStackTrace();
        }
    }

    public boolean isApenasParticipante(Bolao bolao) {

        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();

        if (bolao == null || usuarioLogado == null) {
            return false;
        }

        if (bolao.getDono().equals(usuarioLogado)) {
            return false;
        }

        if (bolao.getParticipantes() == null) {
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

            UtilBean.criarMensagemDeInformacao(
                    "Você saiu do bolão"
            );

            boloes = BOLAO_RN.obter(usuarioLogado);

        } else {

            UtilBean.criarMensagemDeErro(
                    "Não foi possível sair do bolão"
            );
        }
    }

    public String novo() {

        bolao = new Bolao();

        return "formulario.xhtml?faces-redirect=true";
    }
}