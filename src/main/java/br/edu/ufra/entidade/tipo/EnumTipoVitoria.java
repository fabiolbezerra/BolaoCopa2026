package br.edu.ufra.entidade.tipo;

public enum EnumTipoVitoria {
    TEMPO_NORMAL("Vitória no tempo normal."),
    PRORROGACAO("Vitória na prorrogação."),
    PENALTIS("Vitória nos pênaltis.");

    private String descricao;

    EnumTipoVitoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isTempoNormal() {
        return this.equals(TEMPO_NORMAL);
    }

    public boolean isProrrogacao() {
        return this.equals(PRORROGACAO);
    }

    public boolean isPenaltis() {
        return this.equals(PENALTIS);
    }
}
