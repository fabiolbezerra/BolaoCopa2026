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

    public BolaoBean() {
    }

    @PostConstruct
    public void init() {
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();
        try {
            String bolaoID = UtilBean.obterValor("bolaoID");
            if (bolaoID != null && !bolaoID.isEmpty()) {
                int id = Integer.parseInt(bolaoID);
                if (id > 0) {
                    bolao = BOLAO_RN.obter(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (usuarioLogado != null
                && usuarioLogado.getPerfil().isAdministrador()) {
            //Todos os bolões na plataforma
            boloes = BOLAO_RN.listarTodos();
        } else {
            //Bolões do usuário palpiteiro logado
            boloes = BOLAO_RN.obter(usuarioLogado);
        }
    }

    public Bolao getBolao() {
        return bolao;
    }

    public void setBolao(Bolao bolao) {
        this.bolao = BOLAO_RN.obter(bolao.getId());
    }

    public List<Bolao> getBoloes() {
        return boloes;
    }

    public List<Participante> getParticipantes() {
        if (bolao != null) {
            return bolao.getParticipantes();
        }
        return null;
    }

    public void salvar() {
        try {
            Usuario usuarioLogado = UtilBean.obterUsuarioLogado();

            if (usuarioLogado == null) {
                UtilBean.criarMensagemDeErro("Usuário não autenticado");
                return;
            }

            // Apenas dono pode salvar
            if (bolao.getDono() != null
                    && !bolao.getDono().equals(usuarioLogado)) {
                UtilBean.criarMensagemDeErro("Apenas o dono pode salvar o bolão");
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

                BOLAO_RN.adicionarParticipante(bolao, participante);
                boloes = BOLAO_RN.obter(usuarioLogado);

                UtilBean.criarMensagemDeInformacao("Bolão salvo com sucesso!");

                bolao = new Bolao();

            } else {
                UtilBean.criarMensagemDeErro("Não foi possível salvar o bolão");
            }

        } catch (Exception e) {
            UtilBean.criarMensagemDeErro("Erro ao salvar o bolão");
            e.printStackTrace();
        }
    }

    public void excluir() {
        if (BOLAO_RN.excluir(bolao)) {
            UtilBean.criarMensagemDeInformacao("Bolão excluído com sucesso!");
            boloes = BOLAO_RN.obter(UtilBean.obterUsuarioLogado());
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível excluir o bolão selecionado!");
        }
    }

    public boolean isApenasParticipante(Bolao bolao) {
        Usuario usuarioLogado = UtilBean.obterUsuarioLogado();

        if (bolao == null || bolao.getParticipantes() == null || usuarioLogado == null) {
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