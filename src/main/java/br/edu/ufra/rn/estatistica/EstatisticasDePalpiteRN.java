package br.edu.ufra.rn.estatistica;

import br.edu.ufra.dao.PalpiteDAO;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.util.UtilData;

import java.util.Date;

public class EstatisticasDePalpiteRN {
    private final PalpiteDAO PALPITE_DAO = new PalpiteDAO();

    public long obterNovosPalpites24h() {
        Date hoje = new Date();
        Date umDia = UtilData.adicionarDias(hoje, -1);
        return PALPITE_DAO.obterPalpites(hoje, umDia);
    }

    public long obterNumeroDePalpites() {
        return PALPITE_DAO.obterNumeroDePalpites();
    }

    public long obterAlteracoesDePalpites24h() {
        Date hoje = new Date();
        Date umDia = UtilData.adicionarDias(hoje, -1);
        return PALPITE_DAO.obterAlteracoesDePalpites(hoje, umDia);
    }

    public long obterNumeroDePalpites(Partida partida) {
        return 0;
    }
}
