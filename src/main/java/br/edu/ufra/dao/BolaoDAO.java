package br.edu.ufra.dao;

import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Curso;
import br.edu.ufra.entidade.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class BolaoDAO extends GenericDAO<Bolao> {
    public List<Bolao> obter(Usuario usuario) {
        EntityManager em = getEntityManager();
        final String JPQL = "select p.bolao from Participante p where p.usuario = :usuario";
        Query query = em.createQuery(JPQL);
        List<Bolao> resposta = null;
        try {
            resposta = (List<Bolao>) query
                    .setParameter("usuario", usuario)
                    .getResultList();
            return resposta;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public boolean adicionarMembro(Usuario usuario, Bolao bolao) {
        return false;
    }
}
