package br.edu.ufra.rn;

import br.edu.ufra.dao.PartidaDAO;
import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.rn.processamento.*;

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

    private int fibonacci(int n) {
        //0 1 1 2 3 5 8 13
        //1 2 3 4 5 6 7 8
        int primeiro = 0;
        int segundo = 1;
        int resultado = primeiro + segundo;
        if (n <= 2) {
            if (n == 1) {
                resultado = 0;
            } else if (n == 2) {
                resultado = 1;
            }
        } else {
            for (int i = 3; i <= n; i++) {
                resultado = primeiro + segundo;
                primeiro = segundo;
                segundo = resultado;
            }
        }
        return resultado;
    }

    public boolean processar(Partida partida) {
        if (partida == null
                || partida.getId() == null
                || partida.getGolsA() == null
                || partida.getGolsB() == null) {
            return false;
        }
        final PalpiteRN PALPITE_RN = new PalpiteRN();

        List<Palpite> palpites = PALPITE_RN.obter(partida);

        ManipuladorBuilder manipuladorBuilder = new ManipuladorBuilder();
        manipuladorBuilder
                .adicionarManipulador(new ManipuladorTipoVitoria())
                .adicionarManipulador(new ManipuladorGolsA())
                .adicionarManipulador(new ManipuladorGolsB())
                .adicionarManipulador(new ManipuladorPlacarVencedor())
                .adicionarManipulador(new ManipuladorPlacarExato());
        ManipuladorResultadoAbstrato manipuladorDaPontuacao = manipuladorBuilder.construir();

        int pontuacao = 0;
        for (Palpite palpite : palpites) {
            //Manipulador identifica qual índice, da série de fibonacci, interessa
            //Os dois primeiros números da série são poucos pontos, então soma-se dois
            pontuacao = fibonacci(2 + manipuladorDaPontuacao.processar(palpite));
            palpite.setPontuacao(pontuacao);
        }
        //Pontuação dos palpites processada, então salvar os palpites
        boolean palpitesAtualizados = PALPITE_RN.salvar(palpites);

        //TODO: Falta ordenar palpiteiros após atualização dos palpites
        return false;
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
