import inf.unideb.arajanlat.AnyagokServiceJPA;
import inf.unideb.arajanlat.ArajanlatServiceJPA;
import inf.unideb.arajanlat.JpaService;
import inf.unideb.arajanlat.model.Anyagok;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElsoTest {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ArajanlatProgram");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    AnyagokServiceJPA anyagokServiceJPA = new AnyagokServiceJPA(entityManager);

    @Test
    public void testEntityManagerFactory(){
        assertTrue(entityManagerFactory != null);
    }

    @Test
    public void testEntityManager(){
        assertTrue(entityManager != null);
    }

    @Test
    public void testAnyagokService(){
        assertTrue(anyagokServiceJPA != null);
    }
    
    @Test
    public void anyagokCreateTest(){
        List<Anyagok> osszes = anyagokServiceJPA.getOsszesAnyag();
        for(int i=0;i<osszes.size();i++){
            if(osszes.get(i).getAnyagNeve().equals("Teszt")){
                entityManager.getTransaction().begin();
                entityManager.remove(osszes.get(i));
                entityManager.getTransaction().commit();
                assertTrue(true);
                return;
            }
        }
        anyagokServiceJPA.ujAnyagLetrehozasa("Teszt","Teszt",1,1,"Teszt");

        osszes = anyagokServiceJPA.getOsszesAnyag();
        for(int i=0;i<osszes.size();i++){
            if(osszes.get(i).getAnyagNeve().equals("Teszt")){
                entityManager.getTransaction().begin();
                entityManager.remove(osszes.get(i));
                entityManager.getTransaction().commit();
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);
    }


}
