package br.edu.ufra.rn;

import br.edu.ufra.dao.PalpiteDAO;
import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.tipo.EnumTipoVitoria;

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
        if (palpite == null
                || palpite.getPartida() == null
                || palpite.getUsuario() == null) {
            return false;
        }
        //Restrição: Salvar palpite até 30 minutos antes do início da partida
        Calendar trintaMinutesAntes = Calendar.getInstance();
        trintaMinutesAntes.setTime(palpite.getPartida().getInicioDaPartida());
        trintaMinutesAntes.add(Calendar.MINUTE, -30);

        if (new Date().after(trintaMinutesAntes.getTime())) {
            return false;
        }

        if (!palpite.getPartida().getFase().isEliminatoria()) {
            //Fase de grupos, sempre o tipo de vitório é TEMPO_NORMAL
            palpite.setTipoDeVitoria(EnumTipoVitoria.TEMPO_NORMAL);
        } else if (palpite.getTipoDeVitoria() == null) {
            //Fase elimitório o palpite deve vir preenchido
            return false;
        }

        if (palpite.getId() == null || palpite.getId() == 0) {
            palpite.setDataDoPalpite(new Date());
            palpite.setUltimaAlteracao(new Date());
            return PALPITE_DAO.criar(palpite);
        } else {
            palpite.setUltimaAlteracao(new Date());
            return PALPITE_DAO.alterar(palpite);
        }
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

    public boolean excluir(Palpite palpite) {
        if (palpite == null || palpite.getId() == null) {
            return false;
        }
        return PALPITE_DAO.excluir(palpite);
    }
}