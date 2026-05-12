package br.edu.ufra.rn;

import br.edu.ufra.dao.GenericDAO;
import br.edu.ufra.entidade.Fase;

import java.util.List;

public class FaseRN {

    private final GenericDAO<Fase> FASE_DAO = new GenericDAO<>();

    public boolean salvar(Fase fase) {
        if (fase == null) {
            return false;
        }
        if (fase.getId() == null || fase.getId() == 0) {
            return FASE_DAO.criar(fase);
        }
        return FASE_DAO.alterar(fase);
    }

    public List<Fase> listarTodos() {
        return FASE_DAO.obterTodos(Fase.class);
    }

    public Fase obter(Integer id) {
        if (id == null || id == 0) {
            return null;
        }
        return FASE_DAO.obter(Fase.class, id);
    }

    public boolean excluir(Fase fase) {
        if (fase == null) {
            return false;
        }
        return FASE_DAO.excluir(fase);
    }
}