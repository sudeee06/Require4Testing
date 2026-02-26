package bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Requirement;
import util.JpaUtil;

import java.util.List;

@Named
@RequestScoped
public class RequirementBean {

    private Requirement requirement = new Requirement();
    private List<Requirement> requirements;

    @PostConstruct
    public void init() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        requirements = em.createQuery("SELECT r FROM Requirement r", Requirement.class)
                .getResultList();
        em.close();
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void save() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(requirement);
        et.commit();
        em.close();

        requirement = new Requirement();
    }
}