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

        final String JPQL = "select distinct b from Bolao b " +
                "left join b.participantes p " +
                "where b.dono = :usuario or p.usuario = :usuario";

        //String SQL = "select p.bolao from Participante p where p.usuario = :usuario";

        Query query = em.createQuery(JPQL);

        List<Bolao> resposta = null;

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

    public boolean adicionarMembro(Usuario usuario, Bolao bolao) {

        EntityManager em = getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            Bolao bolaoGerenciado = em.merge(bolao);

            Participante participante = new Participante();

            participante.setUsuario(usuario);
            participante.setBolao(bolaoGerenciado);

            em.persist(participante);

            transaction.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace(System.err);

            if (transaction.isActive()) {
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

            Participante participanteGerenciado = em.merge(participante);

            em.remove(participanteGerenciado);

            transaction.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace(System.err);

            if (transaction.isActive()) {
                transaction.rollback();
            }

            return false;

        } finally {

            em.close();
        }
    }

//    public boolean salvar(Bolao bolao) {
//        EntityManager em = getEntityManager();
//
//        EntityTransaction transaction = em.getTransaction();
//
//        try {
//
//            transaction.begin();
//
//            if (bolao.getId() == null) {
//
//                em.persist(bolao);
//
//            } else {
//
//                Bolao bolaoBanco = em.find(Bolao.class, bolao.getId());
//
//                if (bolaoBanco != null) {
//
//                    bolaoBanco.setNome(bolao.getNome());
//                    bolaoBanco.setDono(bolao.getDono());
//                    bolaoBanco.setCriacao(bolao.getCriacao());
//
//                    em.merge(bolaoBanco);
//
//                } else {
//
//                    em.persist(bolao);
//                }
//            }
//
//            transaction.commit();
//
//            return true;
//
//        } catch (Exception e) {
//
//            e.printStackTrace(System.err);
//
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//
//            return false;
//
//        } finally {
//
//            em.close();
//        }
//    }

    public boolean excluir(Bolao bolao) {

        EntityManager em = getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            Bolao bolaoGerenciado = em.merge(bolao);

            em.createQuery(
                            "delete from Participante p where p.bolao = :bolao"
                    )
                    .setParameter("bolao", bolaoGerenciado)
                    .executeUpdate();

            em.remove(bolaoGerenciado);

            transaction.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace(System.err);

            if (transaction.isActive()) {
                transaction.rollback();
            }

            return false;

        } finally {

            em.close();
        }
    }
}