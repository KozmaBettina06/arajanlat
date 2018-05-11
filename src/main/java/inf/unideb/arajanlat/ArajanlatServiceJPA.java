package inf.unideb.arajanlat;

import inf.unideb.arajanlat.model.Arajanlat;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Stefyy on 2017.12.30..
 */
public class ArajanlatServiceJPA {

    EntityManager entityManager;

    public ArajanlatServiceJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
