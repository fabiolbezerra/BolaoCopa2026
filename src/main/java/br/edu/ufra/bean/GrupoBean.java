package br.edu.ufra.bean;

import br.edu.ufra.entidade.Grupo;
import br.edu.ufra.entidade.Selecao;
import br.edu.ufra.rn.GrupoRN;
import br.edu.ufra.rn.SelecaoRN;
import br.edu.ufra.util.UtilBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class GrupoBean {

    private List<Grupo> grupos;

    private Grupo grupo;

    private final GrupoRN GRUPO_RN = new GrupoRN();

    public GrupoBean() {
    }

    @PostConstruct
    public void init() {
        grupos = GRUPO_RN.listarTodos();
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}