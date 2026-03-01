package bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import model.TestRun;
import model.TestCase;
import util.JpaUtil;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class TestRunBean {

    private TestRun testRun = new TestRun();
    private List<Long> selectedTestCaseIds;

    public TestRun getTestRun() {
        return testRun;
    }

    public List<Long> getSelectedTestCaseIds() {
        return selectedTestCaseIds;
    }

    public void setSelectedTestCaseIds(List<Long> selectedTestCaseIds) {
        this.selectedTestCaseIds = selectedTestCaseIds;
    }

    public List<TestRun> getAllTestRuns() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<TestRun> list = em.createQuery(
                        "SELECT tr FROM TestRun tr", TestRun.class)
                .getResultList();
        em.close();
        return list;
    }

    public List<TestCase> getAllTestCases() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<TestCase> list = em.createQuery(
                        "SELECT tc FROM TestCase tc", TestCase.class)
                .getResultList();
        em.close();
        return list;
    }

    public String save() {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        if (selectedTestCaseIds != null && !selectedTestCaseIds.isEmpty()) {
            List<TestCase> selectedCases = selectedTestCaseIds.stream()
                    .map(id -> em.find(TestCase.class, id))
                    .collect(Collectors.toList());
            testRun.setTestCases(selectedCases);
        }

        em.persist(testRun);
        em.getTransaction().commit();
        em.close();

        testRun = new TestRun();
        selectedTestCaseIds = null;

        return "testrun.xhtml?faces-redirect=true";
    }
}