package br.edu.ufra.rn;

import br.edu.ufra.dao.PalpiteDAO;
import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.util.UtilBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PalpiteRN {

    private final PalpiteDAO PALPITE_DAO = new PalpiteDAO();

    public boolean salvar(List<Palpite> palpites) {
        if (palpites == null || palpites.isEmpty()) {
            return false;
        }
        return PALPITE_DAO.salvar(palpites);
    }

    public boolean salvar(Palpite palpite) {
        if (palpite == null || palpite.getPartida() == null) {
            return false;
        }
        Calendar trintaMinutesAntes = Calendar.getInstance();
        trintaMinutesAntes.setTime(palpite.getPartida().getInicioDaPartida());
        trintaMinutesAntes.add(Calendar.MINUTE, -30);

        if (new Date().after(trintaMinutesAntes.getTime())) {
            UtilBean.criarMensagemDeAviso(
                    "Não é possível palpitar 30 minutos antes do início da partida"
            );
            return false;
        }
        palpite.setDataDoPalpite(new Date());
        List<Palpite> lista = new ArrayList<>();
        lista.add(palpite);
        return PALPITE_DAO.salvar(lista);
    }

    public List<Palpite> obter(Partida partida) {
        if (partida == null || partida.getId() == null) {
            return null;
        }
        return PALPITE_DAO.obter(partida);
    }

    public List<Palpite> obter(Usuario palpiteiro) {
        if (palpiteiro == null) {
            return null;
        }
        return PALPITE_DAO.obter(palpiteiro);
    }

    public Palpite obter(Usuario palpiteiro, Partida partida) {
        if (palpiteiro == null || partida == null) {
            return null;
        }
        return PALPITE_DAO.obter(palpiteiro, partida);
    }
}