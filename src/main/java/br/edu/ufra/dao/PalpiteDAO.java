package br.edu.ufra.dao;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PalpiteDAO extends GenericDAO<Palpite> {
    public List<Palpite> obter(Partida partida) {
        EntityManager em = getEntityManager();
        final String JPQL = "SELECT p FROM Palpite p WHERE p.partida >= :partida";
        TypedQuery<Palpite> query = em.createQuery(JPQL, Palpite.class);
        List<Palpite> resposta = null;
        try {
            resposta = query
                    .setParameter("partida", partida)
                    .getResultList();
            return resposta;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
