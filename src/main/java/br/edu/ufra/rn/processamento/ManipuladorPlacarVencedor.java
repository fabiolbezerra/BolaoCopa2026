package br.edu.ufra.rn.processamento;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;

public class ManipuladorPlacarVencedor extends ManipuladorResultadoAbstrato {

    public ManipuladorPlacarVencedor() {
    }

    public ManipuladorPlacarVencedor(ManipuladorResultadoAbstrato proximo) {
        super(proximo);
    }

    private int resultado(Palpite palpite) {
        Integer golsADoPalpite = palpite.getGolsA();
        Integer golsBDoPalpite = palpite.getGolsB();
        if (golsADoPalpite > golsBDoPalpite) {
            return -1; //A venceu
        } else if (golsBDoPalpite > golsADoPalpite) {
            return 1; //B venceu
        } else {
            return 0; //empate
        }
    }

    private int resultado(Partida partida) {
        Integer golsADaPartida = partida.getGolsA();
        Integer golsBDaPartida = partida.getGolsB();
        if (golsADaPartida > golsBDaPartida) {
            return -1; //A venceu
        } else if (golsBDaPartida > golsADaPartida) {
            return 1; //B venceu
        } else {
            return 0; //empate
        }
    }

    @Override
    public int processar(Palpite palpite) {
        if (!palpiteValido(palpite)) {
            return 0;
        }

        int resultadoPalpite = resultado(palpite);
        int resultadoPartida = resultado(palpite.getPartida());

        int resultado = resultadoPalpite == resultadoPartida ? 1 : 0;
        if (getProximo() != null) {
            resultado += getProximo().processar(palpite);
        }
        return resultado;
    }
}
