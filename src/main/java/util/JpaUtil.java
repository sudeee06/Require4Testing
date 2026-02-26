package util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JpaUtil {
    private static final EntityManagerFactory emf = buildFactory();
    private static EntityManagerFactory buildFactory() {
        Map<String, Object> properties = new HashMap<>();

        String password = System.getenv("DB_PASSWORD");
        properties.put("jakarta.persistence.jdbc.password", password);

        return Persistence.createEntityManagerFactory("require4testingPU", properties);
    }
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
