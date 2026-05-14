package br.edu.ufra.dao;

import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Curso;
import br.edu.ufra.entidade.Participante;
import br.edu.ufra.entidade.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            Participante p  = new Participante();
            p.setUsuario(usuario);
            p.setBolao(bolao);

            transaction.begin();
            em.persist(p);
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

    public boolean removerMembro(Participante participante) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            Participante p = em.merge(participante);
            em.remove(p);
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
}
