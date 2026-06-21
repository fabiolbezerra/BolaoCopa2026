package br.edu.ufra.dao;

import br.edu.ufra.entidade.Palpite;
import br.edu.ufra.entidade.Partida;
import br.edu.ufra.entidade.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final String JPQL = "SELECT p FROM Palpite p WHERE p.partida = :partida ORDER BY p.pontuacao DESC";
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

    public List<Palpite> obter(Usuario usuario) {
        EntityManager em = getEntityManager();
        final String JPQL = "SELECT p FROM Palpite p WHERE p.usuario = :usuario";
        TypedQuery<Palpite> query = em.createQuery(JPQL, Palpite.class);
        List<Palpite> resposta = null;
        try {
            resposta = query
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

    public Palpite obter(Usuario usuario, Partida partida) {
        EntityManager em = getEntityManager();
        final String JPQL = "SELECT p FROM Palpite p WHERE p.usuario = :usuario AND p.partida = :partida";
        TypedQuery<Palpite> query = em.createQuery(JPQL, Palpite.class);
        Palpite resposta = null;
        try {
            List<Palpite> resultados = query
                    .setParameter("usuario", usuario)
                    .setParameter("partida", partida)
                    .getResultList();
            if (resultados != null && !resultados.isEmpty()) {
                resposta = resultados.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public Long obterPalpites(Date dataInicio, Date dataFim) {
        String JPQL = "select count(p) from Palpite p where p.dataDoPalpite >= :dataInicio and p.dataDoPalpite <= :dataFim";
        EntityManager em = getEntityManager();
        final Query query = em.createQuery(JPQL);
        Long resposta = 0L;
        Object respostaConsulta = null;
        try {
            respostaConsulta = query
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getSingleResult();
            resposta = ((Number) respostaConsulta).longValue();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public Long obterNumeroDePalpites() {
        String JPQL = "select count(p) from Palpite p";
        EntityManager em = getEntityManager();
        final Query query = em.createQuery(JPQL);
        Long resposta = 0L;
        Object respostaConsulta = null;
        try {
            respostaConsulta = query
                    .getSingleResult();
            resposta = ((Number) respostaConsulta).longValue();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public Long obterAlteracoesDePalpites(Date dataInicio, Date dataFim) {
        String JPQL = "select count(p) from Palpite p where p.ultimaAlteracao >=  :dataInicio and p.ultimaAlteracao <= :dataFim";
        EntityManager em = getEntityManager();
        final Query query = em.createQuery(JPQL);
        Long resposta = 0L;
        Object respostaConsulta = null;
        try {
            respostaConsulta = query
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getSingleResult();
            resposta = ((Number) respostaConsulta).longValue();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public Map<Integer, Integer> obterPalpitesPorPontuacao(Partida partida) {
        final String JPQL = "select p.pontuacao, count(p.pontuacao) from Palpite p " +
                "where p.partida  = :partida " +
                "group by p.pontuacao order by p.pontuacao desc";
        EntityManager em = getEntityManager();
        final Query query = em.createQuery(JPQL);
        Map<Integer, Integer> resposta = null;
        List<Object[]> respostaConsulta = null;
        try {
            respostaConsulta = query
                    .setParameter("partida", partida)
                    .getResultList();
            if (respostaConsulta != null && !respostaConsulta.isEmpty()) {
                resposta = new HashMap<>();
                Integer chave = null;
                Integer valor = null;
                for (Object[] registro : respostaConsulta) {
                    if (registro[0] == null || registro[1] == null) {
                        continue;
                    }
                    chave = ((Number) registro[0]).intValue();
                    valor = ((Number) registro[1]).intValue();
                    resposta.put(chave, valor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

}
