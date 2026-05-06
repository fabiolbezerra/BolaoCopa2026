package br.edu.ufra.entidade.tipo;

public enum EnumTipoUsuario {
    DISCENTE('D', "Discente/Aluno"),
    DOCENTE('P', "Docente/Professor"),
    TECNICO('T', "Técnico Administrativo/TAE");

    private char codigo;
    private String descricao;

    EnumTipoUsuario(char codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public char getCodigo() {
        return codigo;
    }


    public String getDescricao() {
        return descricao;
    }

    public boolean isDocente() {
        return this == DOCENTE;
    }

    public boolean isDiscente() {
        return this == DISCENTE;
    }

    public boolean isTecnico() {
        return this == TECNICO;
    }
}
