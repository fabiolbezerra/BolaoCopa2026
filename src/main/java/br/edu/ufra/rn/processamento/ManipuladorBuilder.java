package br.edu.ufra.rn.processamento;

public class ManipuladorBuilder {
    private ManipuladorResultadoAbstrato manipulador;

    public ManipuladorBuilder() {
    }

    public ManipuladorBuilder(ManipuladorResultadoAbstrato manipulador) {
        this.manipulador = manipulador;
    }

    public ManipuladorBuilder adicionarManipulador(ManipuladorResultadoAbstrato manipulador) {
        if (this.manipulador == null) {
            this.manipulador = manipulador;
        } else {
            ManipuladorResultadoAbstrato ultimo = this.manipulador;
            ManipuladorResultadoAbstrato proximo = this.manipulador.getProximo();
            while (proximo != null) {
                //Último é aquele que o próximo é nulo
                ultimo = proximo;
                proximo = proximo.getProximo();
            }
            ultimo.setProximo(manipulador);
        }
        return this;
    }

    public ManipuladorResultadoAbstrato construir() {
        return this.manipulador;
    }
}
