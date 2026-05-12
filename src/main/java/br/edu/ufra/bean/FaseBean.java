package br.edu.ufra.bean;

import br.edu.ufra.entidade.Fase;
import br.edu.ufra.rn.FaseRN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class FaseBean implements Serializable {

    private Fase fase;
    private List<Fase> fases;

    private final FaseRN RN = new FaseRN();

    @PostConstruct
    public void init() {

        fase = new Fase();

        carregarFases();
    }

    public void carregarFases() {
        fases = RN.listar();
    }

    public void salvar() {

        boolean resultado = RN.salvar(fase);

        if (resultado) {

            fase = new Fase();

            carregarFases();
        }
    }

    public void editar(Fase faseSelecionada) {

        if (faseSelecionada != null) {
            this.fase = faseSelecionada;
        }
    }

    public void excluir(Fase faseSelecionada) {

        if (faseSelecionada != null) {

            RN.excluir(faseSelecionada);

            carregarFases();
        }
    }

    public void novo() {
        fase = new Fase();
    }


    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public List<Fase> getFases() {
        return fases;
    }

    public void setFases(List<Fase> fases) {
        this.fases = fases;
    }
}