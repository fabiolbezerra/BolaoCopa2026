package br.edu.ufra.rn;

import br.edu.ufra.dao.BolaoDAO;
import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Participante;
import br.edu.ufra.entidade.Usuario;

import java.util.Date;
import java.util.List;

public class BolaoRN {

    private final BolaoDAO DAO = new BolaoDAO();

    public boolean salvar(Bolao bolao) {
        if (bolao == null) {
            return false;
        }

        if (bolao.getId() == null || bolao.getId() == 0) {
            //Criar
            bolao.setCriacao(new Date());

//            Participante participante = new Participante();
//            participante.setUsuario(bolao.getDono());
//            participante.setBolao(bolao);

            return DAO.criar(bolao);
        } else {
            //Alterar
            return DAO.alterar(bolao);
        }
    }

    public boolean excluir(Bolao bolao) {
        if (bolao == null || bolao.getId() == null) {
            return false;
        }

        return DAO.excluir(bolao);
    }

    public List<Bolao> obter(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return DAO.obter(usuario);
    }

    public Bolao obter(Integer id) {
        if (id == null) {
            return null;
        }

        return DAO.obter(Bolao.class, id);
    }

    public List<Bolao> listarTodos() {
        return DAO.obterTodos(Bolao.class);
    }

    public boolean adicionarParticipante(Bolao bolao,
                                         Participante participante) {
        if (bolao == null
                || participante == null
                || participante.getUsuario() == null
                || participante.getBolao() == null) {

            return false;
        }

        if (bolao.getParticipantes() != null) {

            for (Participante p : bolao.getParticipantes()) {

                if (p.getUsuario().equals(
                        participante.getUsuario()
                )) {

                    return false;
                }
            }
        }

        return DAO.adicionarMembro(participante.getUsuario(), participante.getBolao());
    }

    public boolean removerParticipante(Participante participante, Bolao bolao) {
        if (participante == null || bolao == null) {
            return false;
        }

        return DAO.removerMembro(participante);
    }

    public boolean sairDoBolao(
            Bolao bolao,
            Usuario usuario
    ) {

        if (bolao == null || usuario == null) {
            return false;
        }

        Participante participanteParaSair = null;

        if (bolao.getParticipantes() != null) {

            for (Participante p : bolao.getParticipantes()) {

                if (p.getUsuario().equals(usuario)) {

                    participanteParaSair = p;

                    break;
                }
            }
        }

        if (participanteParaSair == null) {
            return false;
        }

        return DAO.removerMembro(participanteParaSair);
    }
}