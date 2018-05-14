package inf.unideb.arajanlat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by Stefyy on 2017.12.30..
 */

/**
 * olyan osztály amely az adatbázis kapcsolatot biztosítja.
 *
 */
public class JpaService {

    private static JpaService jpaServiceInstance = new JpaService();

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private AnyagokServiceJPA anyagokServiceJPA = null;
    private ArajanlatServiceJPA arajanlatServiceJPA = null;

    /**
     * megadja a jpaServiceIstance értékét.
     *
     * @return visszatlr az értékkel
     */
    public static JpaService getJpaServiceInstance() {
        return jpaServiceInstance;
    }

    /**
     * beállítja a jpaServiceInstance értékét.
     *
     * @param jpaServiceInstance megkapja a jpaServiceInstance értékét
     */
    public static void setJpaServiceInstance(JpaService jpaServiceInstance) {
        JpaService.jpaServiceInstance = jpaServiceInstance;
    }

    /**
     * megadja az EntityManagerFactory értékét.
     *
     * @return visszatér az értékkel
     */
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    /**
     * beállítja az entityManagerFactory értékét.
     *
     * @param entityManagerFactory megkapja az entityManagerFactory értékét
     */
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * megadja az EntityManager értékét.
     *
     * @return visszatér az értékkel
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * beállítja az EntityManager értékét.
     *
     * @param entityManager megkapja az EntityManager értékét.
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * megadja az AnagyokServiceJPA értékét.
     *
     * @return visszatér AnagyokServiceJPA értékével
     */
    public AnyagokServiceJPA getAnyagokServiceJPA() {
        return anyagokServiceJPA;
    }

    /**
     * beállítja az AnagyokServiceJPA értékét.
     *
     * @param anyagokServiceJPA megkapja AnagyokServiceJPA értékét
     */
    public void setAnyagokServiceJPA(AnyagokServiceJPA anyagokServiceJPA) {
        this.anyagokServiceJPA = anyagokServiceJPA;
    }

    /**
     * megadja ArajanlatServiceJPA értékét.
     *
     * @return visszatér ArajanlatServiceJPA értékével
     */
    public ArajanlatServiceJPA getArajanlatServiceJPA() {
        return arajanlatServiceJPA;
    }

    /**
     * beállítja ArajanlatServiceJPA értékét.
     *
     * @param arajanlatServiceJPA megkapja ArajanlatServiceJPA értékét
     */
    public void setArajanlatServiceJPA(ArajanlatServiceJPA arajanlatServiceJPA) {
        this.arajanlatServiceJPA = arajanlatServiceJPA;
    }
}
