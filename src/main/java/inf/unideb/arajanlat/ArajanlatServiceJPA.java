package inf.unideb.arajanlat;

import inf.unideb.arajanlat.model.Arajanlat;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Stefyy on 2017.12.30..
 */

/**
 * az árajánlatok adatbázis elérését biztosító osztály.
 *
 */
public class ArajanlatServiceJPA {

    EntityManager entityManager;

    /**
     * konstruktor.
     *
     * @param entityManager megkapja az entityManagert
     */
    public ArajanlatServiceJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * az elkészült árajánlat lista elemeit feltölti az adatbázisba.
     *
     * @param list megkapja az ajánlat listát.
     */
    public void ujArajanlatLetrehozasa(List<Arajanlat> list) {
        for (Arajanlat all : list) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(all);
                entityManager.getTransaction().commit();
            } catch (EntityExistsException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * listát készít az azonos árajánlatú nevekről.
     *
     * @param arajanlatNeve megkapja az árajánlott nevet
     * @return visszatér az eredményül kapott listával.
     */
    public List<Arajanlat> arajanlatNevSzerint(String arajanlatNeve){
        Query query = null;

        try {
            query = entityManager.createQuery("select e from Arajanlat e where arajanlatNeve like :arajanlatNev",Arajanlat.class);
            query.setParameter("arajanlatNev",arajanlatNeve);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

    /**
     * összes árajanlatfajta kilistázva egyszer.
     *
     * @return visszatér a különböző árajánlat nevekkel
     */
    public List<String> osszesArajanlat(){
        Query query = null;
        try {
            query = entityManager.createQuery("select distinct e.arajanlatNeve from Arajanlat e");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

}
