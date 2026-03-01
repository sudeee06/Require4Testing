package bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import model.TestRun;
import util.JpaUtil;

import java.util.List;

@Named
@RequestScoped
public class OverviewBean {

    public List<TestRun> getAllTestRuns() {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        List<TestRun> list = em.createQuery(
                "SELECT DISTINCT tr FROM TestRun tr LEFT JOIN FETCH tr.testCases",
                TestRun.class
        ).getResultList();

        em.close();
        return list;
    }
}
