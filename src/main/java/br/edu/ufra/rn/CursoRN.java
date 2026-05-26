package br.edu.ufra.rn;

import br.edu.ufra.dao.CursoDAO;
import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Curso;

import java.util.List;

public class CursoRN {

    private final CursoDAO CURSO_DAO = new CursoDAO();


    public List<Curso> listarTodos() {
        return CURSO_DAO.obterTodos(Curso.class);
    }

    public Curso obter(Integer id) {
        return CURSO_DAO.obter(Curso.class, id);
    }
    
    public List<Curso> obter(Campus campus) {
        if (campus == null || campus.getId() == null || campus.getId() == 0) {
            return null;
        }
        return CURSO_DAO.obter(campus);
    }
}

