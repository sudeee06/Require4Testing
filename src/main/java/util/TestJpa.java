package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Requirement;

public class TestJpa {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();

        Requirement req = new Requirement();
        req.setTitle("Login-Funktion testen");
        req.setDescription("Es soll geprüft werden, ob der Benutzer sich einloggen kann.");

        em.persist(req);
        et.commit();
        em.close();

        System.out.println("Requirement gespeichert.");


    }
}
