package inf.unideb.arajanlat;

import inf.unideb.arajanlat.model.Anyagok;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Stefyy on 2017.12.30..
 */

/**
 * az anyag adatbázishoz biztosítja a kapcsolatot.
 *
 */
public class AnyagokServiceJPA {

    EntityManager entityManager;

    /**
     * konstruktor.
     *
     * @param entityManager megkapja az entitymanagert
     */
    public AnyagokServiceJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * új anyag létrehozása.
     *
     * @param anyagNeve anyag neve
     * @param kategoria kategoria
     * @param egyseg egység
     * @param ar ar
     * @param mertekegyseg mértékegység
     * @return visszatér az anyaggal
     */
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

    /**
     * az anyag fel persistelése a database-be.
     *
     * @param anyagok várja az anyagt.
     */
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

    /**
     * listát készít a megadott kategória szerint az anyagokból.
     *
     * @param kategoriaNeve megkapja a kategóriát
     * @return visszatér a  lekérdezés listájával.
     */
    public List<Anyagok> anyagokKategoriaSzerint(String kategoriaNeve){
        Query query = null;

        try {
            query = entityManager.createQuery("select e from Anyagok e where kategoria like :kategoriaNev",Anyagok.class);
            query.setParameter("kategoriaNev", kategoriaNeve);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

    /**
     * megadja az összes anyaggal.
     *
     * @return visszatér az összes anyaggal
     */
    public List<Anyagok> getOsszesAnyag(){
        Query query = null;

        try {
            query = entityManager.createQuery("select e from Anyagok e");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

}
