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
@ViewScoped
public class BolaoBean {

    private final BolaoRN BOLAO_RN = new BolaoRN();

    private Bolao bolao = new Bolao();
    private Participante participante = new Participante();

    private List<Bolao> boloes;
    private List<Participante> participantes;

    public BolaoBean() {
    }

    @PostConstruct
    public void init() {
        String bolaoID = UtilBean.obterValor("bolaoID");
        try {
            this.bolao = BOLAO_RN.obter(Integer.parseInt(bolaoID));
            this.participantes = this.bolao.getParticipantes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        participante.setBolao(bolao);

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
        this.bolao = bolao;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
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

    public void adicionarParticipante() {
        if (participante == null
                || participante.getBolao() == null
                || participante.getBolao().getId() == null
                || participante.getUsuario() == null
                || participante.getUsuario().getId() == null) {
            UtilBean.criarMensagemDeAviso("Dados incompletos", "Participante é uma combinação de bolão e palpiteiro");
        } else {
            if (BOLAO_RN.adicionarParticipante(bolao, participante)) {
                UtilBean.criarMensagemDeInformacao("Um novo participante foi adicionado ao bolão");

                //Atualizando bolão, participante e lista de participantes do bolão
                bolao = BOLAO_RN.obter(bolao.getId());
                participante = new Participante();
                participante.setBolao(bolao);
                participantes = bolao.getParticipantes();
            } else {
                UtilBean.criarMensagemDeAviso("Um usuário não pode ser duplicado na lista de membros");
                UtilBean.criarMensagemDeErro("Problema no banco do dados ao tentar incluir o participante");
            }
        }
    }
}
