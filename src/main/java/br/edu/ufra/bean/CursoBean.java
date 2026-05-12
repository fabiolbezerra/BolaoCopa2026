package br.edu.ufra.bean;

import br.edu.ufra.entidade.Curso;
import br.edu.ufra.rn.CursoRN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class CursoBean {

    private final CursoRN CURSO_RN = new CursoRN();
    private List<Curso> cursos;
    private Curso curso = new Curso();

    public CursoBean() {
    }

    @PostConstruct
    public void init() {
        cursos = CURSO_RN.listar();
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public boolean isNovoCurso() {
        return this.curso != null
                && (this.curso.getId() == null || this.curso.getId() == 0);
    }
}
