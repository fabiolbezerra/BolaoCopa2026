package br.edu.ufra.rn.estatistica;

import br.edu.ufra.dao.PalpiteDAO;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.util.UtilData;

import java.util.Date;
import java.util.Map;

public class EstatisticasDePalpiteRN {
    private final PalpiteDAO PALPITE_DAO = new PalpiteDAO();

    public long obterNovosPalpites24h() {
        Date hoje = new Date();
        Date _24hAtras = UtilData.adicionarDias(hoje, -1);
        return PALPITE_DAO.obterPalpites(_24hAtras, hoje);
    }

    public long obterNumeroDePalpites() {
        return PALPITE_DAO.obterNumeroDePalpites();
    }

    public long obterAlteracoesDePalpites24h() {
        Date hoje = new Date();
        Date _24hAtras = UtilData.adicionarDias(hoje, -1);
        return PALPITE_DAO.obterAlteracoesDePalpites(_24hAtras, hoje);
    }

    public Map<Integer, Integer> obterPalpitesPorPontuacao(Partida partida) {
        if (partida == null || partida.getId() == null || partida.getId() == 0) {
            return null;
        }
        Map<Integer, Integer> resposta = PALPITE_DAO.obterPalpitesPorPontuacao(partida);
        Integer[] fibonacci = {0, 1, 2, 3, 5, 8, 13};
        for (Integer key : fibonacci) {
            resposta.putIfAbsent(key, 0);
        }
        return resposta;
    }
}
