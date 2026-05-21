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
            if (partida.getId() == null || partida.getId() == 0) {
                return this.PARTIDA_DAO.alterar(partida);
            } else {
                return this.PARTIDA_DAO.criar(partida);
            }
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

    public List<Partida> obterProximasOitoPartidas() {
        Date hoje = new Date();
        List<Partida> partidas = PARTIDA_DAO.obterProximasPartidas(hoje);
        if (partidas == null || partidas.size() <= 8) {
            return partidas;
        }
        return partidas.subList(0, 8);
    }

    public List<Partida> obterProximasPartidas() {
        return null; //TODO Pra ti Paulo
    }

    public List<Partida> obterPartidasAnteriores() {
        return null; //TODO Pra ti Paulo
    }
}
