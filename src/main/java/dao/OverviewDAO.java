package dao;

import jakarta.persistence.EntityManager;
import model.TestRun;
import util.JpaUtil;

import java.util.List;

public class OverviewDAO {

    /**
     * Lädt alle Testläufe und die verknüpften Testfälle.
     * Nutzt JOIN FETCH, um das N+1-Performance-Problem zu vermeiden.
     */
    public List<TestRun> findAllWithTestCases() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //JPQL-Abfrage
            return em.createQuery(
                    "SELECT DISTINCT tr FROM TestRun tr LEFT JOIN FETCH tr.testCases",
                    TestRun.class
            ).getResultList();
        } finally {
            em.close();
        }
    }
}