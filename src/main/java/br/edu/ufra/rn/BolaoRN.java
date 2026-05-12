package br.edu.ufra.rn;

import br.edu.ufra.dao.BolaoDAO;
import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Usuario;

import java.util.List;

public class BolaoRN {
    private final BolaoDAO DAO = new BolaoDAO();

    public List<Bolao> obter(Usuario usuario) {
        if (usuario == null || usuario.getPerfil().isAdministrador()) {
            //Apenas palpiteiros participam em bolões
            return null;
        } else {
            return DAO.obter(usuario);
        }
    }

    public List<Bolao> listarTodos() {
        return DAO.obterTodos(Bolao.class);
    }
}
