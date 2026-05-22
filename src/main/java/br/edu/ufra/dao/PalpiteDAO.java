package br.edu.ufra.dao;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PalpiteDAO extends GenericDAO<Palpite> {

    public boolean salvar(List<Palpite> palpites) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            for (Palpite palpite : palpites) {
                em.merge(palpite);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            if (transaction != null
                    && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

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
