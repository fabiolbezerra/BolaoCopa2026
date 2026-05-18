package br.edu.ufra.rn;

import br.edu.ufra.dao.BolaoDAO;
import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Participante;
import br.edu.ufra.entidade.Usuario;

import java.util.Date;
import java.util.List;

public class BolaoRN {
    private final BolaoDAO DAO = new BolaoDAO();

    public List<Bolao> obter(Usuario usuario) {
        if (usuario == null || usuario.getPerfil().isAdministrador()) {
            //Apenas palpiteiros participam em bolões
            return null;
        } else {
            return DAO.obter(usuario);
        }
    }

    public Bolao obter(Integer id) {
        if (id == null) {
            return null;
        } else {
            return DAO.obter(Bolao.class, id);
        }
    }

    public List<Bolao> listarTodos() {
        return DAO.obterTodos(Bolao.class);
    }

    public boolean adicionarParticipante(Bolao bolao, Participante participante) {
        if (bolao == null
                || participante == null
                || participante.getUsuario() == null
                || participante.getBolao() == null) {
            return false;
        } else {
            for (Participante p : bolao.getParticipantes()) {
                if (p.getUsuario().equals(participante.getUsuario())) {
                    //Participante já está no bolão
                    return false;
                }
            }
        }
        return DAO.adicionarMembro(participante.getUsuario(), participante.getBolao());
    }

    public boolean sairDoBolao(Bolao bolao, Usuario usuario) {
        if (bolao == null || usuario == null) {
            return false;
        }
        Participante participanteParaSair = null;
        for (Participante p : bolao.getParticipantes()) {
            if (p.getUsuario().equals(usuario)) {
                participanteParaSair = p;
                break;
            }
        }
        if (participanteParaSair == null) {
            return false;
        }
        return DAO.removerMembro(participanteParaSair);
    }
    public boolean removerParticipante(Participante participante, Bolao bolao) {
        if (participante == null || participante.getBolao() == null) {
            return false;
        }
        if (! bolao.getParticipantes().contains(participante)) {
            return false;
        }return DAO.removerMembro(participante);
    }

    public boolean excluir(Bolao bolao){
        if (bolao == null) {
            return false;
        } else {
            return DAO.excluir(bolao);
        }
    }
    public boolean salvar(Bolao bolao){
        if(bolao == null){
            return false;
        }else{
            Date criacao = new Date();
            Date dataCriacao;
            dataCriacao = criacao;
            return DAO.criar(bolao);
        }
    }

}
