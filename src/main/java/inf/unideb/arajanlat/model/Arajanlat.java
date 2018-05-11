package inf.unideb.arajanlat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Stefyy on 2018. 05. 07..
 */

/**
 * Entity osztály az árajánlatoknak.
 *
 */
@Entity
public class Arajanlat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String anyagNeve;
    private String mertekegyseg;
    private String kategoria;
    private int mennyiseg;
    private int egyseg;
    private int egysegar;
    private String arajanlatNeve;

    /**
     * paraméter nélküli konstrultor.
     *
     */
    public Arajanlat() {
    }

    /**
     * konstruktor.
     *
     * @param anyagNeve az anyag neve
     * @param mertekegyseg az anyag mértékegysége.
     * @param kategoria az anyag kategóriája
     * @param mennyiseg az anyag mennyisége
     * @param egyseg az anyag egysége, pl 2m egy egység
     * @param egysegar az anyga egységára
     * @param arajanlatNeve az árajánlat neve
     */
    public Arajanlat(String anyagNeve, String mertekegyseg, String kategoria, int mennyiseg, int egyseg, int egysegar, String arajanlatNeve) {
        this.anyagNeve = anyagNeve;
        this.mertekegyseg = mertekegyseg;
        this.kategoria = kategoria;
        this.mennyiseg = mennyiseg;
        this.egyseg = egyseg;
        this.egysegar = egysegar;
        this.arajanlatNeve = arajanlatNeve;
    }

    /**
     * konstruktor, arra az esetre, hogy ha módosítjuk a régi árajánlatot
     * akkor hozzon létre új példányt és ne a régit változtassa.
     *
     * @param arajanlat egy korábbi ráajánlat példány
     */
    public Arajanlat(Arajanlat arajanlat){
        this.anyagNeve = arajanlat.anyagNeve;
        this.mertekegyseg = arajanlat.mertekegyseg;
        this.kategoria = arajanlat.kategoria;
        this.mennyiseg = arajanlat.mennyiseg;
        this.egyseg = arajanlat.egyseg;
        this.egysegar = arajanlat.egysegar;
        this.arajanlatNeve = arajanlat.arajanlatNeve;
    }

    /**
     * megadja az azonosítót.
     *
     * @return visszatér az azonosítóval
     */
    public long getId() {
        return id;
    }

    /**
     * beállítja az azonosítót.
     *
     * @param id megkapja az azonosítót
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * megadja az anyagnevet.
     *
     * @return visszatér az anyagnévvel
     */
    public String getAnyagNeve() {
        return anyagNeve;
    }

    /**
     * beállítja az anyagnevet.
     *
     * @param anyagNeve megkapja az anyagnevet
     */
    public void setAnyagNeve(String anyagNeve) {
        this.anyagNeve = anyagNeve;
    }
    /**
     * megadja a mértékegységet.
     *
     * @return visszatér a mértékegységet
     */
    public String getMertekegyseg() {
        return mertekegyseg;
    }

    /**
     * beállítja a mértékegységet.
     *
     * @param mertekegyseg megkapja a mértékegységet
     */
    public void setMertekegyseg(String mertekegyseg) {
        this.mertekegyseg = mertekegyseg;
    }
    /**
     * megadja a kategóriát.
     *
     * @return visszatér a kategóriával
     */
    public String getKategoria() {
        return kategoria;
    }

    /**
     * beállítja a kategóriát.
     *
     * @param kategoria megkapja a kategóriát
     */
    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
    /**
     * beállítja a mennyiséget.
     *
     * @return visszatér a mennyiséggel
     */
    public int getMennyiseg() {
        return mennyiseg;
    }

    /**
     * beállítja a mennyiséget.
     *
     * @param mennyiseg megkapja a mennyiséget
     */
    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }
    /**
     * megadja az egységet.
     *
     * @return visszatér az egységgel
     */
    public int getEgyseg() {
        return egyseg;
    }

    /**
     * beállítja az egységet.
     *
     * @param egyseg megkapja az egyéget
     */
    public void setEgyseg(int egyseg) {
        this.egyseg = egyseg;
    }
    /**
     * megadja az egységárat.
     *
     * @return visszatér az egységárral
     */
    public int getEgysegar() {
        return egysegar;
    }

    /**
     * beállítja az egyégárat.
     *
     * @param egysegar megkapja az egységárat
     */
    public void setEgysegar(int egysegar) {
        this.egysegar = egysegar;
    }
    /**
     * megadja az árajánlat nevét.
     *
     * @return visszatér az árajánlat nevével
     */
    public String getArajanlatNeve() {
        return arajanlatNeve;
    }

    /**
     * beállítja az árajánlat nevét.
     *
     * @param arajanlatNeve megkapja az árajánlat nevét.
     */
    public void setArajanlatNeve(String arajanlatNeve) {
        this.arajanlatNeve = arajanlatNeve;
    }

}
