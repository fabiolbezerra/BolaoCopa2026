package br.edu.ufra.rn;

import br.edu.ufra.dao.GenericDAO;
import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;

import java.util.List;

public class CampusRN {
    
    private final GenericDAO<Campus> DAO = new GenericDAO<>();

    public List<Campus> listarTodos() {
        return DAO.obterTodos(Campus.class);
    }

    public Campus obter(Integer id) {
            return DAO.obter(Campus.class, id);

    }

    public List<Campus> obter(String query) {
        if (query == null || query.trim().equals("")) {
            return null;
        }

        List<Campus> resposta = listarTodos();

        String nome = null;

        for (int i = 0; i < resposta.size(); i++) {
            nome = resposta.get(i).getNome();
            if (!nome.toUpperCase().contains(query.toUpperCase())) {
                resposta.remove(i);
                i--;
            }
        }

        return resposta;
    }
}
