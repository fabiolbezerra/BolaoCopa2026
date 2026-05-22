package br.edu.ufra.rn.processamento;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.tipo.EnumTipoVitoria;

public class ManipuladorTipoVitoria extends ManipuladorResultadoAbstrato {

    public ManipuladorTipoVitoria() {
    }

    public ManipuladorTipoVitoria(ManipuladorResultadoAbstrato proximo) {
        super(proximo);
    }

    @Override
    public int processar(Palpite palpite) {
        if (!palpiteValido(palpite)) {
            return 0;
        }

        EnumTipoVitoria tipoDeVitoriaPartida = palpite.getPartida().getTipoDeVitoria();
        EnumTipoVitoria tipoDeVitoriaPalpite = palpite.getTipoDeVitoria();

        int resultado = tipoDeVitoriaPartida.equals(tipoDeVitoriaPalpite) ? 1 : 0;
        if (getProximo() != null) {
            resultado += getProximo().processar(palpite);
        }
        return resultado;
    }
}
