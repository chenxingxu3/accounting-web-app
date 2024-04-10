package web.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {
	private static javax.persistence.EntityManagerFactory f;

	
    public static EntityManager create() {
        return f.createEntityManager();
    }
	
    public static void initialize(String unitName) {
        f = Persistence.createEntityManagerFactory(unitName);
    }

    public static void destroy() {
        f.close();
    }
}
