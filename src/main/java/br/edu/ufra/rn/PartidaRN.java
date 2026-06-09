package br.edu.ufra.rn;

import br.edu.ufra.dao.PartidaDAO;
import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;
import br.edu.ufra.entidade.comparador.ComparadorUsuarioPontuacao;
import br.edu.ufra.rn.processamento.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PartidaRN {
    private final PartidaDAO PARTIDA_DAO = new PartidaDAO();

    public PartidaRN() {
    }

    public boolean salvar(Partida partida) {
        if (partida == null || partida.getSelecaoA() == null || partida.getSelecaoB() == null) {
            return false;
        } else {
            if (partida.getId() == null) {
                return this.PARTIDA_DAO.criar(partida);
            } else {
                return this.PARTIDA_DAO.alterar(partida);
            }
        }
    }

    public List<Partida> listarTodos() {
        return this.PARTIDA_DAO.obterTodos(Partida.class);
    }

    public Partida obter(Integer id) {
        return this.PARTIDA_DAO.obter(Partida.class, id);
    }

    public boolean excluir(Partida partida) {
        if (partida == null || !partida.getPalpites().isEmpty()) {
            return false;
        } else {
            return this.PARTIDA_DAO.excluir(partida);
        }
    }

    private int fibonacci(int n) {
        //0 1 1 2 3 5 8 13 --- valor
        //1 2 3 4 5 6 7 8  --- índices
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

    public boolean processarPontuacao(Partida partida) {
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
                .adicionarManipulador(new ManipuladorPlacarExato())
                .adicionarManipulador(new ManipuladorSelecaoVencedora());
        ManipuladorResultadoAbstrato manipuladorDaPontuacao = manipuladorBuilder.construir();

        int pontuacao = 0;
        for (Palpite palpite : palpites) {
            //Manipulador identifica qual índice, da série de fibonacci, interessa
            //Os dois primeiros números da série são poucos pontos, então soma-se dois
            pontuacao = manipuladorDaPontuacao.processar(palpite);
            if (pontuacao > 0) {
                pontuacao = fibonacci(2 + pontuacao);
            } else {
                pontuacao = 0; //não pontua
            }
            palpite.setPontuacao(pontuacao);
        }
        //Pontuação dos palpites processada, então salvar os palpites
        boolean palpitesAtualizados = PALPITE_RN.salvar(palpites);

        return palpitesAtualizados;
    }

    public boolean processarOrdenacao(Partida partida) {
        if (partida == null
                || partida.getId() == null
                || partida.getGolsA() == null
                || partida.getGolsB() == null) {
            return false;
        }
        final UsuarioRN USUARIO_RN = new UsuarioRN();

        List<Usuario> palpiteiros = USUARIO_RN.obter(partida);

        Integer pontuacao = 0;
        for (Usuario palpiteiro : palpiteiros) {
            pontuacao = USUARIO_RN.obterPontuacao(palpiteiro);
            palpiteiro.setPontuacao(pontuacao);
        }

        //Ordenação inversa da pontuação
        Collections.sort(palpiteiros, new ComparadorUsuarioPontuacao());
        int posicao = 1;
        for (Usuario palpiteiro : palpiteiros) {
            palpiteiro.setPosicao(posicao);
            posicao++;
        }

        //Pontuação dos palpites processada, então salvar os palpites
        boolean usuariosAtualizados = USUARIO_RN.salvar(palpiteiros);
        return usuariosAtualizados;
    }

    public List<Partida> obterProximasOitoPartidas() {
        Date hoje = new Date();
        List<Partida> partidas = PARTIDA_DAO.obterProximasPartidas(hoje, false);
        if (partidas == null || partidas.size() <= 8) {
            return partidas;
        }
        return partidas.subList(0, 8);
    }

    public List<Partida> obterProximasPartidas(boolean administrador) {
        Date hoje = new Date();
        List<Partida> partidas = PARTIDA_DAO.obterProximasPartidas(hoje, administrador);
        return partidas;
    }

    public List<Partida> obterPartidasAnteriores(boolean administrador) {
        Date hoje = new Date();
        List<Partida> partidas = PARTIDA_DAO.obterPartidasAnteriores(hoje, administrador);
        return partidas;
    }

}
