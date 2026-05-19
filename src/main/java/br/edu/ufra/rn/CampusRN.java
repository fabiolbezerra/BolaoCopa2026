package br.edu.ufra.rn;

import br.edu.ufra.dao.GenericDAO;
import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Usuario;

import java.util.List;

public class CampusRN {
    
    private final GenericDAO<Campus> DAO = new GenericDAO<>();

    public List<Campus> listarTodos() {
        return DAO.obterTodos(Campus.class);
    }

    public Campus obter(Integer id) {
            return DAO.obter(Campus.class, id);

    }
}
