package br.edu.ufra.rn;

import br.edu.ufra.dao.PartidaDAO;
import br.edu.ufra.entidade.Partida;
import java.util.Date;
import java.util.List;

public class PartidaRN {
    private final PartidaDAO PARTIDA_DAO = new PartidaDAO();

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
    public List<Partida> listarProximasPartidas() {
        Date hoje = new Date();
        List<Partida> partidas = PARTIDA_DAO.obterProximasPartidas(hoje);
        if (partidas == null || partidas.size() <= 8) {
            return partidas;
        }
        return partidas.subList(0, 8);
    }
}
