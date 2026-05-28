package br.edu.ufra.bean;

import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Participante;
import br.edu.ufra.rn.BolaoRN;
import br.edu.ufra.rn.ParticipanteRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.Objects;

@ManagedBean
@ViewScoped
public class MembroDoBolaoBean {

    private final BolaoRN BOLAO_RN = new BolaoRN();
    private final ParticipanteRN PARTICIPANTE_RN = new ParticipanteRN();

    private Bolao bolao = new Bolao();
    private Participante participante = new Participante();

    private List<Participante> participantes;

    public MembroDoBolaoBean() {
    }

    @PostConstruct
    public void init() {
        String bolaoID = UtilBean.obterValor("bolaoID");
        try {
            bolao = BOLAO_RN.obter(Integer.parseInt(bolaoID));
            participantes = PARTICIPANTE_RN.obter(bolao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        participante.setBolao(bolao);
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

    public List<Participante> getParticipantes() {
        return participantes;
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

                atualizarListaDeParticipantes();
            } else {
                UtilBean.criarMensagemDeAviso("Um usuário não pode ser duplicado na lista de membros");
                UtilBean.criarMensagemDeErro("Problema no banco do dados ao tentar incluir o participante");
            }
        }
    }

    private void atualizarListaDeParticipantes() {
        //Atualizando bolão, participante e lista de participantes do bolão
        bolao = BOLAO_RN.obter(bolao.getId());
        participante = new Participante();
        participante.setBolao(bolao);

        participantes = PARTICIPANTE_RN.obter(bolao);
    }

    public void removerParticipante() {
        if (participante == null) {
            UtilBean.criarMensagemDeAviso("Palpiteiro não encontrado");
        } else if (participante.getBolao() == null
                || ! Objects.equals(participante.getBolao(), bolao)) {
            UtilBean.criarMensagemDeErro("Palpiteiro não participa deste bolão");
        } else {
            if (BOLAO_RN.removerParticipante(participante, bolao)) {
                UtilBean.criarMensagemDeInformacao("Participante removido com sucesso");
            } else {
                UtilBean.criarMensagemDeErro("Não foi possível remover o participante");
            }
        }
        atualizarListaDeParticipantes();
    }
}
