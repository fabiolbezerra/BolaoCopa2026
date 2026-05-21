package br.edu.ufra.rn;

import br.edu.ufra.dao.PalpiteDAO;
import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;

import java.util.List;

public class PalpiteRN {
    private final PalpiteDAO PALPITE_DAO = new PalpiteDAO();

    public List<Palpite> obter(Partida partida) {
        if (partida == null || partida.getId() == null) {
            return null;
        } else {
            return PALPITE_DAO.obter(partida);
        }
    }
}
