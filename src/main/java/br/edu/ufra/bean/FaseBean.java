package br.edu.ufra.bean;

import br.edu.ufra.entidade.Fase;
import br.edu.ufra.rn.FaseRN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class FaseBean implements Serializable {

    private Fase fase;
    private List<Fase> fases;

    private final FaseRN RN = new FaseRN();

    @PostConstruct
    public void init() {
        fases = RN.listarTodos();
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