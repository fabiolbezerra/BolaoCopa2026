package br.edu.ufra.bean;

import br.edu.ufra.entidade.Campus;
import br.edu.ufra.rn.CampusRN;
import br.edu.ufra.rn.CursoRN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class CampusBean {

    private final CampusRN CAMPUS_RN = new CampusRN();
    private List<Campus> campi;
    private Campus campus = new Campus();

    public CampusBean() {
    }

    @PostConstruct
    public void init() {
        campi = CAMPUS_RN.listarTodos();
    }

    public List<Campus> getCampi() {
        return campi;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCurso(Campus campus) {
        this.campus = campus;
    }

}

