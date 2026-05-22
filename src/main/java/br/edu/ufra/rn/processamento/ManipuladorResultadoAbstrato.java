package br.edu.ufra.rn.processamento;

import br.edu.ufra.entidade.Palpite;

public abstract class ManipuladorResultadoAbstrato {
    private ManipuladorResultadoAbstrato proximo;

    public ManipuladorResultadoAbstrato() {
    }

    public ManipuladorResultadoAbstrato(ManipuladorResultadoAbstrato proximo) {
        this.proximo = proximo;
    }

    public ManipuladorResultadoAbstrato getProximo() {
        return proximo;
    }

    public void setProximo(ManipuladorResultadoAbstrato proximo) {
        this.proximo = proximo;
    }

    protected boolean palpiteValido(Palpite palpite) {
        if (palpite == null
                || palpite.getId() == null
                || palpite.getPartida() == null) {
            return false;
        } else {
            return true;
        }
    }

    public abstract int processar(Palpite palpite);
}
