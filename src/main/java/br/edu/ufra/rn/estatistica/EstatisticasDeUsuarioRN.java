package br.edu.ufra.rn.estatistica;

import br.edu.ufra.dao.UsuarioDAO;
import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Curso;
import br.edu.ufra.util.UtilData;

import java.util.Date;

public class EstatisticasDeUsuarioRN {
    private final UsuarioDAO USUARIO_DAO = new UsuarioDAO();

    public long obterNovosCadastros24h() {
        Date hoje = new Date();
        Date umDia = UtilData.adicionarDias(hoje, -1);
        return USUARIO_DAO.obterCadastros(hoje, umDia);
    }

    public long obterNumeroDeCadastros() {
        return USUARIO_DAO.obterNumeroDeCadastros();
    }

    public long obterNumeroDeCadastros(Campus campus) {
        return 0;
    }

    public long obterNumeroDeCadastros(Campus campus, Curso curso) {
        return 0;
    }

    public long obterNumeroDeUsuariosBanidos24h() {
        Date hoje = new Date();
        Date umDia = UtilData.adicionarDias(hoje, -1);
        return USUARIO_DAO.obterBanimentos(hoje, umDia);
    }

    public long obterNumeroDeUsuariosBanidos() {
        return USUARIO_DAO.obterNumeroDeBanimentos();
    }
}
