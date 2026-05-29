package br.edu.ufra.dao;

import br.edu.ufra.entidade.*;
import br.edu.ufra.entidade.tipo.EnumPerfilUsuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Fabio
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    public boolean salvar(List<Usuario> usuarios) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;
        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return false;
        }
        try {
            transaction.begin();
            for (Usuario usuario : usuarios) {
                em.merge(usuario);
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

    public Usuario login(String email, String senha) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = null;

        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return null;
        }

        final String JPQL = "select u from Usuario u where u.email = :email and u.senha = :senha";
        Query query = em.createQuery(JPQL);

        Usuario resposta = null;

        try {
            transaction.begin();

            List<Usuario> usuarios =
                    (List<Usuario>) query
                            .setParameter("email", email)
                            .setParameter("senha", senha)
                            .getResultList();

            if (usuarios != null && usuarios.size() == 1) {
                resposta = usuarios.get(0);
                em.refresh(resposta);
            }

            transaction.commit();

            return resposta;

        } catch (Exception e) {
            e.printStackTrace(System.err);

            if (transaction != null
                    && transaction.isActive()) {

                transaction.rollback();
            }

        } finally {
            em.close();
        }

        return resposta;
    }

    public Usuario obter(String email) {
        EntityManager em = getEntityManager();

        String JPQL = "select o from Usuario o where o.email = :email";
        Query query = em.createQuery(JPQL);

        Usuario resposta = null;
        try {

            List<Usuario> usuarios =
                    (List<Usuario>) query
                            .setParameter("email", email)
                            .getResultList();

            if (usuarios != null && usuarios.size() == 1) {
                resposta = usuarios.get(0);
                em.refresh(resposta);
            }

            return resposta;

        } catch (Exception e) {

            e.printStackTrace(System.err);

        } finally {
            em.close();
        }

        return resposta;
    }

    public List<Usuario> obter(EnumPerfilUsuario perfil) {
        EntityManager em = getEntityManager();

        final String JPQL = "select o from Usuario o where o.perfil = :perfil";

        TypedQuery<Usuario> query = em.createQuery(JPQL, Usuario.class);

        List<Usuario> resposta = null;

        try {

            resposta = query
                    .setParameter("perfil", perfil)
                    .getResultList();

            return resposta;

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }

        return resposta;
    }

    public List<Usuario> pesquisar(String chave) {
        EntityManager em = getEntityManager();

        final String JPQL = "select o from Usuario o " +
                "left join o.curso c " + //left porque nem sempre o usuário terá um curso
                "join o.campus cc " +
                "where (o.nome like :chave or o.sobrenome like :chave) " +
                "or o.email like :chave " +
                "or c.nome like :chave " +
                "or cc.nome like :chave " +
                "order by o.nome asc";

        TypedQuery<Usuario> query = em.createQuery(JPQL, Usuario.class);

        List<Usuario> resposta = null;

        try {
            String chaveGenerica = "%" + chave + "%";
            resposta = query
                    .setParameter("chave", chaveGenerica)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }

        return resposta;
    }

    public List<Usuario> obter(Partida partida) {
        EntityManager em = getEntityManager();

        final String JPQL = "select p.usuario from Palpite p where p.partida = :partida";
        TypedQuery<Usuario> query = em.createQuery(JPQL, Usuario.class);

        List<Usuario> resposta = null;

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

    public Integer obterPontuacao(Usuario palpiteiro) {
        EntityManager em = getEntityManager();

        final String JPQL = "select sum(p.pontuacao) from Palpite p where p.usuario = :palpiteiro";
        TypedQuery<Long> query = em.createQuery(JPQL, Long.class);

        Long resposta = 0L;

        try {
            resposta = query
                    .setParameter("palpiteiro", palpiteiro)
                    .getSingleResult();
            if (resposta == null) {
                resposta = 0L;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }

        return resposta.intValue();
    }

    public List<Usuario> obterOrdenadosPorPontuacao() {
        EntityManager em = getEntityManager();

        final String JPQL = "select u from Usuario u where u.perfil = :perfil order by u.pontuacao desc";
        TypedQuery<Usuario> query = em.createQuery(JPQL, Usuario.class);

        List<Usuario> resposta = null;

        try {
            resposta = query
                    .setParameter("perfil", EnumPerfilUsuario.PALPITEIRO)
                    .getResultList();

            return resposta;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }

        return resposta;
    }

    public List<Usuario> obterOrdenadosPorPontuacao(Campus campus) {
        EntityManager em = getEntityManager();

        final String JPQL = "select u from Usuario u " +
                "where u.perfil = :perfil and u.campus = :campus " +
                "order by u.pontuacao desc";
        TypedQuery<Usuario> query = em.createQuery(JPQL, Usuario.class);

        List<Usuario> resposta = null;

        try {
            resposta = query
                    .setParameter("perfil", EnumPerfilUsuario.PALPITEIRO)
                    .setParameter("campus", campus)
                    .getResultList();

            return resposta;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }

        return resposta;
    }

    public List<Usuario> obterOrdenadosPorPontuacao(Curso curso) {
        EntityManager em = getEntityManager();

        final String JPQL = "select u from Usuario u " +
                "where u.perfil = :perfil and u.curso = :curso " +
                "order by u.pontuacao desc";
        TypedQuery<Usuario> query = em.createQuery(JPQL, Usuario.class);

        List<Usuario> resposta = null;

        try {
            resposta = query
                    .setParameter("perfil", EnumPerfilUsuario.PALPITEIRO)
                    .setParameter("curso", curso)
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