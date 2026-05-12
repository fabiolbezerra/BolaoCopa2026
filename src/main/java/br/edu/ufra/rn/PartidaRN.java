package br.edu.ufra.rn;

import br.edu.ufra.dao.GenericDAO;
import br.edu.ufra.entidade.Partida;

import java.util.List;

public class PartidaRN {
    private final GenericDAO<Partida> PARTIDA_DAO = new GenericDAO();

    public PartidaRN() {
    }

    public boolean salvar(Partida partida) {
        if (partida == null) {
            return false;
        } else {
            return partida.getId() != null && partida.getId() != 0 ? this.PARTIDA_DAO.alterar(partida) : this.PARTIDA_DAO.criar(partida);
        }
    }

    public List<Partida> listarTodos() {
        return this.PARTIDA_DAO.obterTodos(Partida.class);
    }

    public Partida obter(Integer id) {
        return (Partida) this.PARTIDA_DAO.obter(Partida.class, id);
    }

    public boolean excluir(Partida partida) {
        return partida == null ? false : this.PARTIDA_DAO.excluir(partida);
    }
}
