package br.edu.ufra.rn.processamento;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Selecao;

import java.util.Objects;

/**
 * Manipulador do cálculo da pontuação para placares construídos fora do tempo normal do
 * jogo. Ou seja, na prorrogação ou pênaltis.
 * <p>
 * Seleção vencedora é a seleção classificada para a próxima fase. Independente se na
 * prorrogação ou nos pênaltis.
 */
public class ManipuladorSelecaoVencedora extends ManipuladorResultadoAbstrato {

    public ManipuladorSelecaoVencedora() {
    }

    public ManipuladorSelecaoVencedora(ManipuladorResultadoAbstrato proximo) {
        super(proximo);
    }

    @Override
    public int processar(Palpite palpite) {
        if (!palpiteValido(palpite)) {
            return 0;
        }

        int resultado = 0;

        //Fase eliminatória avalia seleção vencedora da prorrogação ou pênaltis
        if (palpite.getPartida().getFase().isEliminatoria()
                && !palpite.getPartida().getTipoDeVitoria().isTempoNormal()
                && !palpite.getTipoDeVitoria().isTempoNormal()) {
            Selecao vencedor = palpite.getPartida().getVencedor();
            Selecao selecaoVencedora = palpite.getSelecaoVencedora();
            resultado = Objects.equals(vencedor, selecaoVencedora) ? 1 : 0;
        }

        if (getProximo() != null) {
            resultado += getProximo().processar(palpite);
        }

        return resultado;
    }
}
