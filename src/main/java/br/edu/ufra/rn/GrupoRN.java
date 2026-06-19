package br.edu.ufra.rn;

import br.edu.ufra.dao.GenericDAO;
import br.edu.ufra.entidade.Grupo;

import java.util.List;

public class GrupoRN {

    private final GenericDAO<Grupo> DAO = new GenericDAO<>();

    public List<Grupo> listarTodos() {
        return DAO.obterTodos(Grupo.class);
    }

    public Grupo obter(Integer id) {
        return DAO.obter(Grupo.class, id);

    }

}
