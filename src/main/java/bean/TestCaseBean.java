package bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import model.TestCase;
import util.JpaUtil;
import model.Requirement;

import java.util.List;

@Named
@RequestScoped

public class TestCaseBean {

    public List<TestCase> getAllTestCases() {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<TestCase> list = em.createQuery("SELECT t FROM TestCase t", TestCase.class).getResultList();
        em.close();
        return list;
    }

    private TestCase testCase = new TestCase();

    public TestCase getTestCase() {
        return testCase;
    }

    private Long selectedRequirementId;

    public Long getSelectedRequirementId() {
        return selectedRequirementId;
    }

    public void setSelectedRequirementId(Long selectedRequirementId) {
        this.selectedRequirementId = selectedRequirementId;
    }


    public String save() {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Requirement req = em.find(Requirement.class, selectedRequirementId);
        testCase.setRequirement(req);

        em.persist(testCase);
        em.getTransaction().commit();
        em.close();

        testCase = new TestCase();

        return "testcases.xhtml?faces-redirect=true";
    }

    public List<Requirement> getAllRequirements() {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<Requirement> list = em.createQuery("SELECT r FROM Requirement r", Requirement.class).getResultList();
        em.close();

        return list;
    }
}