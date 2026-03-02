package dao;

import jakarta.persistence.EntityManager;
import model.Requirement;
import util.JpaUtil;

import java.util.List;

public class RequirementDAO {

    //Methode zum Speichern oder Aktualisieren einer Anforderung
    public void save(Requirement requirement) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();

            //Wenn Objekt bereits ID hat, dann merge, sonst perisist nutzen
            if (requirement.getId() == null) {
                em.persist(requirement);
            } else {
                em.merge(requirement);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); //Sicherheit bei Fehlern
            }

            e.printStackTrace();
        } finally {
            em.close(); //EntityMamnager immer schließen
        }
    }

    /**
     * Methode zum Auslesen aller Anforderungen aus der Datenbank.
     * Nutzt JPQL (Jakarta Persistence Query Language)
     */
    public List<Requirement> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Erzeugt eine typsichere Abfrage für alle Requirement-Objekte
            return em.createQuery("SELECT r FROM Requirement r", Requirement.class).getResultList();
        } finally {
            em.close();
        }
    }
}