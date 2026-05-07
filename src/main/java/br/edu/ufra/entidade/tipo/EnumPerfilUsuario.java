package br.edu.ufra.entidade.tipo;

public enum EnumPerfilUsuario {
    ADMINISTRADOR('A', "Administrador da plataforma", "/administrador/principal.xhtml"),
    PALPITEIRO('P', "Palpiteiro(a) nas partidas", "/palpiteiro/principal.xhtml");

    private char codigo;
    private String descricao;
    private String pagina;

    EnumPerfilUsuario(char codigo, String descricao, String pagina) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.pagina = pagina;
    }

    public char getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPagina() { return pagina; }

    public boolean isPalpiteiro() {
        return this == PALPITEIRO;
    }

    public boolean isAdministrador() {
        return this == ADMINISTRADOR;
    }
}
