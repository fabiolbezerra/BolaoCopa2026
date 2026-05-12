package br.edu.ufra.dao;

import br.edu.ufra.entidade.Campus;
import br.edu.ufra.entidade.Curso;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CursoDAO extends GenericDAO<Curso>{
    public List<Curso> obter(Campus campus) {
        EntityManager em = getEntityManager();
        final String JPQL = "select c from Curso c where c.campus = :campus ";
        Query query = em.createQuery(JPQL);
        List<Curso> resposta = null;
        try {
            resposta = (List<Curso>) query
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
}
