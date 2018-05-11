package inf.unideb.arajanlat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by Stefyy on 2017.12.30..
 */
public class JpaService {

    private static JpaService jpaServiceInstance = new JpaService();

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private AnyagokServiceJPA anyagokServiceJPA = null;
    private ArajanlatServiceJPA arajanlatServiceJPA = null;

    public static JpaService getJpaServiceInstance() {
        return jpaServiceInstance;
    }

    public static void setJpaServiceInstance(JpaService jpaServiceInstance) {
        JpaService.jpaServiceInstance = jpaServiceInstance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public AnyagokServiceJPA getAnyagokServiceJPA() {
        return anyagokServiceJPA;
    }

    public void setAnyagokServiceJPA(AnyagokServiceJPA anyagokServiceJPA) {
        this.anyagokServiceJPA = anyagokServiceJPA;
    }

    public ArajanlatServiceJPA getArajanlatServiceJPA() {
        return arajanlatServiceJPA;
    }

    public void setArajanlatServiceJPA(ArajanlatServiceJPA arajanlatServiceJPA) {
        this.arajanlatServiceJPA = arajanlatServiceJPA;
    }
}
