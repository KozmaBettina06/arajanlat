package inf.unideb.arajanlat.model;

/**
 * Created by Stefyy on 2018. 05. 07..
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity osztály az alapanyagokhoz.
 *
 */
@Entity
public class Anyagok {
    /**
     * paraméter nélküli konstruktor.
     *
     */
    public Anyagok() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String anyagNeve;
    private String kategoria;
    private int egyseg;
    private int ar;
    private String mertekegyseg;

    /**
     * konstruktor.
     *
     * @param anyagNeve az anyag neve
     * @param kategoria anyagkategória
     * @param egyseg egység, pl 2 méter egy egység
     * @param ar anyag ára
     * @param mertekegyseg az anyga mértékegysége
     */
    public Anyagok(String anyagNeve, String kategoria, int egyseg, int ar, String mertekegyseg) {
        this.anyagNeve = anyagNeve;
        this.kategoria = kategoria;
        this.egyseg = egyseg;
        this.ar = ar;
        this.mertekegyseg = mertekegyseg;
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
     * beállítja az azonosító értékét.
     *
     * @param id az azonosítót kapja meg.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * megadja az anyag nevét.
     *
     * @return az anyga nevével tér vissza
     */
    public String getAnyagNeve() {
        return anyagNeve;
    }

    /**
     *  beállítja az anyga nevét.
     *
     * @param anyagNeve az anyag nevét kapja meg
     */
    public void setAnyagNeve(String anyagNeve) {
        this.anyagNeve = anyagNeve;
    }

    /**
     * megadja a kategóriát.
     *
     * @return visszatér az anyag kategóriával
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
     * megadja az egység értékét.
     *
     * @return visszatér az egység értékével
     */
    public int getEgyseg() {
        return egyseg;
    }

    /**
     * beállítja az egység értékét.
     *
     * @param egyseg az egység értékét kapja meg
     */
    public void setEgyseg(int egyseg) {
        this.egyseg = egyseg;
    }

    /**
     * megadja az anyag  árát.
     *
     * @return visszatér az anyag árával
     */
    public int getAr() {
        return ar;
    }

    /**
     * beállítja az anyag árát.
     *
     * @param ar megkapja az árat
     */
    public void setAr(int ar) {
        this.ar = ar;
    }

    /**
     * megadja a mértékegységet.
     *
     * @return visszatér a mértékegységgel
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
}
