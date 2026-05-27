package br.edu.ufra.dao;

import br.edu.ufra.entidade.Partida;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PartidaDAO extends GenericDAO<Partida> {

    public List<Partida> obterProximasPartidas(Date data, boolean administrador) {
        EntityManager em = getEntityManager();
        String JPQL = null;
        if (administrador) {
            JPQL = "SELECT p FROM Partida p WHERE p.inicioDaPartida >= :data ORDER BY p.inicioDaPartida ASC";
        } else {
            JPQL = "SELECT p FROM Partida p WHERE p.inicioDaPartida >= :data and p.publicada = true ORDER BY p.inicioDaPartida ASC";
        }
        TypedQuery<Partida> query = em.createQuery(JPQL, Partida.class);
        List<Partida> resposta = null;
        try {
            resposta = query
                    .setParameter("data", data)
                    .getResultList();
            return resposta;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public List<Partida> obterPartidasAnteriores(Date data, boolean administrador) {
        EntityManager em = getEntityManager();
        String JPQL = null;
        if (administrador) {
            JPQL = "SELECT p FROM Partida p WHERE p.inicioDaPartida < :data ORDER BY p.inicioDaPartida ASC";
        } else {
            JPQL = "SELECT p FROM Partida p WHERE p.inicioDaPartida < :data and p.publicada = true ORDER BY p.inicioDaPartida ASC";
        }
        TypedQuery<Partida> query = em.createQuery(JPQL, Partida.class);

        List<Partida> resposta = null;
        try {
            resposta = query
                    .setParameter("data", data)
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