package br.edu.ufra.dao;

import br.edu.ufra.entidade.Bolao;
import br.edu.ufra.entidade.Participante;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ParticipanteDAO extends GenericDAO<Participante> {
    public List<Participante> obter(Bolao bolao) {
        EntityManager em = getEntityManager();

        final String JPQL = "select p from Participante p " +
                "where p.bolao = :bolao " +
                "order by p.usuario.pontuacao desc, p.usuario.posicao ";
        TypedQuery<Participante> query = em.createQuery(JPQL, Participante.class);

        List<Participante> resposta = null;

        try {
            resposta = query
                    .setParameter("bolao", bolao)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }

        return resposta;
    }
}
