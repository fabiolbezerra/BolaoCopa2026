package br.edu.ufra.rn;

import br.edu.ufra.dao.GenericDAO;
import br.edu.ufra.entidade.Selecao;

import java.util.List;

public class SelecaoRN {

    private final GenericDAO<Selecao> DAO = new GenericDAO<>();

    public List<Selecao> listarTodos() {
        return DAO.obterTodos(Selecao.class);
    }

    public Selecao obter(Integer id) {
        return DAO.obter(Selecao.class, id);
    }

    public boolean salvar(Selecao selecao) {
        if (selecao == null) {
            return false;
        }
        if (selecao.getId() == null || selecao.getId() == 0) {
            return DAO.criar(selecao);
        } else {
            return DAO.alterar(selecao);
        }
    }

    public boolean excluir(Selecao selecao) {
        if (selecao == null) {
            return false;
        }
        return DAO.excluir(selecao);
    }
}