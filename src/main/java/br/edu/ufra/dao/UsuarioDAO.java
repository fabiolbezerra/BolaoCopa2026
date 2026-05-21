package br.edu.ufra.dao;

import br.edu.ufra.entidade.Usuario;
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

    public Usuario login(String email, String senha) {

        EntityManager em = getEntityManager();

        EntityTransaction transaction = null;

        if (em != null) {
            transaction = em.getTransaction();
        } else {
            return null;
        }

        String sql =
                "select u from Usuario u " +
                        "where u.email = :email " +
                        "and u.senha = :senha";

        Query query = em.createQuery(sql);

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

        String sql =
                "select o from Usuario o " +
                        "where o.email = :email";

        Query query = em.createQuery(sql);

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

        final String JPQL =
                "select o from Usuario o " +
                        "where o.perfil = :perfil";

        TypedQuery<Usuario> query =
                em.createQuery(JPQL, Usuario.class);

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

    public List<Usuario> obterOrdenadosPorPontuacao() {

        EntityManager em = getEntityManager();

        final String JPQL =
                "select u from Usuario u " +
                        "order by u.pontuacao desc";

        TypedQuery<Usuario> query =
                em.createQuery(JPQL, Usuario.class);

        List<Usuario> resposta = null;

        try {

            resposta = query.getResultList();

            return resposta;

        } catch (Exception e) {

            e.printStackTrace(System.err);

        } finally {

            em.close();
        }

        return resposta;
    }
}