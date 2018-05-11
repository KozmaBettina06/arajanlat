package inf.unideb.arajanlat;

import inf.unideb.arajanlat.model.Anyagok;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Stefyy on 2017.12.30..
 */
public class AnyagokServiceJPA {

    EntityManager entityManager;

    public AnyagokServiceJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Anyagok ujAnyagLetrehozasa(String anyagNeve ,String kategoria,int egyseg,int ar,String mertekegyseg) {
        Anyagok anyagDatabase = new Anyagok();
        try {
            entityManager.getTransaction().begin();
            anyagDatabase.setAnyagNeve(anyagNeve);
            anyagDatabase.setKategoria(kategoria);
            anyagDatabase.setEgyseg(egyseg);
            anyagDatabase.setAr(ar);
            anyagDatabase.setMertekegyseg(mertekegyseg);
            entityManager.persist(anyagDatabase);
            entityManager.getTransaction().commit();
        }catch (EntityExistsException | IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return anyagDatabase;
    }

    public void ujAnyagLetrehozasa(Anyagok anyagok) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(anyagok);
            entityManager.getTransaction().commit();
        }catch (EntityExistsException | IllegalArgumentException e)
        {
            e.printStackTrace();
        }
    }

    public List<Anyagok> anyagokKategoriaSzerint(String kategoriaNeve){
        Query query = null;

        try {
            query = entityManager.createQuery("select e from Anyagok e where kategoria like :kategoriaNev",Anyagok.class);
            query.setParameter("kategoriaNev",kategoriaNeve);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

}
