package br.edu.ufra.rn;

import br.edu.ufra.dao.ParticipanteDAO;
import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Participante;

import java.util.List;

public class ParticipanteRN {
    private final ParticipanteDAO PARTICIPANTE_DAO = new ParticipanteDAO();
    public List<Participante> obter(Bolao bolao) {
        if (bolao == null || bolao.getId() == null || bolao.getId() == 0) {
            return null;
        } else {
            return PARTICIPANTE_DAO.obter(bolao);
        }
    }
}
