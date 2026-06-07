package br.edu.ufra.rn.estatistica;

import br.edu.ufra.dao.UsuarioDAO;
import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Curso;
import br.edu.ufra.util.UtilData;

import java.util.Date;
import java.util.Map;

public class EstatisticasDeUsuarioRN {
    private final UsuarioDAO USUARIO_DAO = new UsuarioDAO();

    public long obterNovosCadastros24h() {
        Date hoje = new Date();
        Date _24hAtras = UtilData.adicionarDias(hoje, -1);
        return USUARIO_DAO.obterCadastros(_24hAtras, hoje);
    }

    public long obterNumeroDeCadastros() {
        return USUARIO_DAO.obterNumeroDeCadastros();
    }

    public long obterNumeroDeCadastros(Campus campus) {
        if (campus == null || campus.getId() == null || campus.getId() == 0) {
            return 0;
        }
        return USUARIO_DAO.obterNumeroDeCadastros(campus);
    }

    public long obterNumeroDeCadastros(Curso curso) {
        if (curso == null || curso.getId() == null || curso.getId() == 0) {
            return 0;
        }
        return USUARIO_DAO.obterNumeroDeCadastros(curso);
    }

    public long obterNumeroDeUsuariosBanidos24h() {
        Date hoje = new Date();
        Date _24hAtras = UtilData.adicionarDias(hoje, -1);
        return USUARIO_DAO.obterBanimentos(_24hAtras, hoje);
    }

    public long obterNumeroDeUsuariosBanidos() {
        return USUARIO_DAO.obterNumeroDeBanimentos();
    }

    public Map<String, Integer> obterPalpiteirosPorTipo() {
        return USUARIO_DAO.obterCadastrosPorTipoUsuario();
    }

    public Map<String, Integer> obterPalpiteirosPorCampus() {
        return USUARIO_DAO.obterCadastrosPorCampus();
    }

    public Map<String, Integer> obterPalpiteirosPorCurso(Campus campus) {
        if (campus == null || campus.getId() == null || campus.getId() == 0) {
            return null;
        }
        return USUARIO_DAO.obterCadastrosPorCurso(campus);
    }
}
