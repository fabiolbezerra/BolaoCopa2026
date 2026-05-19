package br.edu.ufra.rn;

import br.edu.ufra.dao.CursoDAO;
import br.edu.ufra.entidade.Curso;
import br.edu.ufra.entidade.Curso;

import java.util.List;

public class CursoRN {

    private final CursoDAO DAO = new CursoDAO();


    public List<Curso> listarTodos() {
        return DAO.obterTodos(Curso.class);
    }

    public Curso obter(Integer id) {
        return DAO.obter(Curso.class, id);
    }
    


}

