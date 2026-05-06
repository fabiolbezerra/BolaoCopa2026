package br.edu.ufra.entidade.tipo;

public enum EnumPerfilUsuario {
    ADMINISTRADOR('A', "Administrador da plataforma"),
    PALPITEIRO('P', "Palpiteiro nas partidas");

    private char codigo;
    private String descricao;

    EnumPerfilUsuario(char codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public char getCodigo() {
        return codigo;
    }


    public String getDescricao() {
        return descricao;
    }

    public boolean isPalpiteiro() {
        return this == PALPITEIRO;
    }

    public boolean isAdministrador() {
        return this == ADMINISTRADOR;
    }
}
