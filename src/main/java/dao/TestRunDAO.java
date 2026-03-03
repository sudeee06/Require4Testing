package dao;

import jakarta.persistence.EntityManager;
import model.TestRun;
import model.TestCase;
import util.JpaUtil;

import java.util.List;
import java.util.stream.Collectors;

public class TestRunDAO {

    /**
     * Speichert einen Testlauf inkl. Tester und verknüpft die Testfälle.
     * Nutzt Transaktionen zur Sicherstellung der Datenkonsistenz
     */
    public void save(TestRun testRun, List<Long> testCaseIds) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();


            if (testCaseIds != null && !testCaseIds.isEmpty()) {
                List<TestCase> selectedCases = testCaseIds.stream()
                        .map(id -> em.find(TestCase.class, id))
                        .collect(Collectors.toList());
                testRun.setTestCases(selectedCases);
            }

            if (testRun.getId() == null || testRun.getId() == 0) {
                em.persist(testRun);
            } else {
                em.merge(testRun);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<TestRun> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT tr FROM TestRun tr", TestRun.class).getResultList();
        } finally {
            em.close();
        }
    }
}