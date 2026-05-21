package br.edu.ufra.dao;

import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Participante;
import br.edu.ufra.entidade.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class BolaoDAO extends GenericDAO<Bolao> {

    public List<Bolao> obter(Usuario usuario) {

        EntityManager em = getEntityManager();

        final String JPQL =
                "select distinct b from Bolao b " +
                        "left join b.participantes p " +
                        "where b.dono = :usuario or p.usuario = :usuario";

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

        EntityTransaction transaction = em.getTransaction();

        try {

            Participante participante = new Participante();

            participante.setUsuario(usuario);
            participante.setBolao(bolao);

            transaction.begin();

            em.persist(participante);

            transaction.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace(System.err);

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            return false;

        } finally {

            em.close();
        }
    }

    public boolean removerMembro(Participante participante) {

        EntityManager em = getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            Participante p = em.merge(participante);

            em.remove(p);

            transaction.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace(System.err);

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            return false;

        } finally {

            em.close();
        }
    }

    public boolean salvar(Bolao bolao) {

        EntityManager em = getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            if (bolao.getId() == null) {

                em.persist(bolao);

            } else {

                em.merge(bolao);
            }

            transaction.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace(System.err);

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            return false;

        } finally {

            em.close();
        }
    }

    public boolean excluir(Bolao bolao) {

        EntityManager em = getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            Bolao b = em.merge(bolao);

            em.createQuery(
                            "delete from Participante p where p.bolao = :bolao"
                    )
                    .setParameter("bolao", b)
                    .executeUpdate();

            em.remove(b);

            transaction.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace(System.err);

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            return false;

        } finally {

            em.close();
        }
    }
}