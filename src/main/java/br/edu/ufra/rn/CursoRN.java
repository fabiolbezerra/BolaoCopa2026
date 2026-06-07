package br.edu.ufra.rn;

import br.edu.ufra.dao.CursoDAO;
import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Curso;

import java.util.*;

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

    //obter somente pelo curso
    public List<Curso> obter(String query) {
        if (query == null || query.trim().equals("")) {
            return null;
        }

        List<Curso> cursos = listarTodos();

        String nome = null;

        for (int i = 0; i < cursos.size(); i++) {
            nome = cursos.get(i).getNome() + " " + cursos.get(i).getCampus().getNome();
            if (!nome.toUpperCase().contains(query.toUpperCase())) {
                cursos.remove(i);
                i--;
            }
        }
        List<Curso> resposta = new ArrayList<>();
        Set<String> listaSemRepeticao = new HashSet<>();

        for (Curso curso : cursos) {
            if(listaSemRepeticao.add(curso.getNome())){
                resposta.add(curso);
            }
        }
        return resposta;
    }
}

