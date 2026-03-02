package dao;

import jakarta.persistence.EntityManager;
import model.TestCase;
import model.Requirement;
import util.JpaUtil;

import java.util.List;

public class TestCaseDAO {


    //Speichert einen Testfall und verknüpft ihn mit einer Anforderung (Many-to-One)
    public void save(TestCase testCase, Long requirementId) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();

            //Assoziation herstellen: Anforderung laden und dem Testfall zuweisen
            if (requirementId != null) {
                Requirement req = em.find(Requirement.class, requirementId);
                testCase.setRequirement(req);
            }

            //Unterscheidung zwischen persist (neu) und merge (aktualisieren)
            if (testCase.getId() == null) {
                em.persist(testCase);
            } else {
                em.merge(testCase);
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

    public List<TestCase> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT t FROM TestCase t", TestCase.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Requirement> findAllRequirements() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //JPQL-Abfrage für die Anforderungsliste
            return em.createQuery("SELECT r FROM Requirement r", Requirement.class).getResultList();
        } finally {
            em.close();
        }
    }
}