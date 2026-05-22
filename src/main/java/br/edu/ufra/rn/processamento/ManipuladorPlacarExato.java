package br.edu.ufra.rn.processamento;

import br.edu.ufra.entidade.Palpite;

import java.util.Objects;

public class ManipuladorPlacarExato extends ManipuladorResultadoAbstrato {

    public ManipuladorPlacarExato() {
    }

    public ManipuladorPlacarExato(ManipuladorResultadoAbstrato proximo) {
        super(proximo);
    }

    @Override
    public int processar(Palpite palpite) {
        if (!palpiteValido(palpite)) {
            return 0;
        }

        int resultado = 0;
        if (Objects.equals(palpite.getGolsA(), palpite.getPartida().getGolsA())
                && Objects.equals(palpite.getGolsB(), palpite.getPartida().getGolsB())) {
            resultado = 1;
        }

        if (getProximo() != null) {
            resultado += getProximo().processar(palpite);
        }
        return resultado;
    }
}
