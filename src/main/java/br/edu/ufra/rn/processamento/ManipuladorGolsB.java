package br.edu.ufra.rn.processamento;

import br.edu.ufra.entidade.Palpite;

public class ManipuladorGolsB extends ManipuladorResultadoAbstrato {

    public ManipuladorGolsB() {
    }

    public ManipuladorGolsB(ManipuladorResultadoAbstrato proximo) {
        super(proximo);
    }

    @Override
    public int processar(Palpite palpite) {
        if (!palpiteValido(palpite)) {
            return 0;
        }

        int golsBPalpite = palpite.getGolsB();
        int golsBPartiida = palpite.getPartida().getGolsB();

        int resultado = golsBPalpite == golsBPartiida ? 1 : 0;
        if (getProximo() != null) {
            resultado += getProximo().processar(palpite);
        }
        return resultado;
    }
}
