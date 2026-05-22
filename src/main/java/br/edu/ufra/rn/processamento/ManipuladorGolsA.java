package br.edu.ufra.rn.processamento;

import br.edu.ufra.entidade.Palpite;

public class ManipuladorGolsA extends ManipuladorResultadoAbstrato {

    public ManipuladorGolsA() {
    }

    public ManipuladorGolsA(ManipuladorResultadoAbstrato proximo) {
        super(proximo);
    }

    @Override
    public int processar(Palpite palpite) {
        if (!palpiteValido(palpite)) {
            return 0;
        }

        int golsAPalpite = palpite.getGolsA();
        int golsAPartiida = palpite.getPartida().getGolsA();

        int resultado = golsAPalpite == golsAPartiida ? 1 : 0;
        if (getProximo() != null) {
            resultado += getProximo().processar(palpite);
        }
        return resultado;
    }
}
