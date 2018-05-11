package inf.unideb.arajanlat.model;

/**
 * Created by Stefyy on 2018. 05. 07..
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity oszt�ly az alapanyagokhoz.
 *
 */
@Entity
public class Anyagok {
    /**
     * param�ter n�lk�li konstruktor.
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
     * @param kategoria anyagkateg�ria
     * @param egyseg egys�g, pl 2 m�ter egy egys�g
     * @param ar anyag �ra
     * @param mertekegyseg az anyga m�rt�kegys�ge
     */
    public Anyagok(String anyagNeve, String kategoria, int egyseg, int ar, String mertekegyseg) {
        this.anyagNeve = anyagNeve;
        this.kategoria = kategoria;
        this.egyseg = egyseg;
        this.ar = ar;
        this.mertekegyseg = mertekegyseg;
    }

    /**
     * megadja az azonos�t�t.
     *
     * @return visszat�r az azonos�t�val
     */
    public long getId() {
        return id;
    }

    /**
     * be�ll�tja az azonos�t� �rt�k�t.
     *
     * @param id az azonos�t�t kapja meg.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * megadja az anyag nev�t.
     *
     * @return az anyga nev�vel t�r vissza
     */
    public String getAnyagNeve() {
        return anyagNeve;
    }

    /**
     *  be�ll�tja az anyga nev�t.
     *
     * @param anyagNeve az anyag nev�t kapja meg
     */
    public void setAnyagNeve(String anyagNeve) {
        this.anyagNeve = anyagNeve;
    }

    /**
     * megadja a kateg�ri�t.
     *
     * @return visszat�r az anyag kateg�ri�val
     */
    public String getKategoria() {
        return kategoria;
    }

    /**
     * be�ll�tja a kateg�ri�t.
     *
     * @param kategoria megkapja a kateg�ri�t
     */
    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    /**
     * megadja az egys�g �rt�k�t.
     *
     * @return visszat�r az egys�g �rt�k�vel
     */
    public int getEgyseg() {
        return egyseg;
    }

    /**
     * be�ll�tja az egys�g �rt�k�t.
     *
     * @param egyseg az egys�g �rt�k�t kapja meg
     */
    public void setEgyseg(int egyseg) {
        this.egyseg = egyseg;
    }

    /**
     * megadja az anyag  �r�t.
     *
     * @return visszat�r az anyag �r�val
     */
    public int getAr() {
        return ar;
    }

    /**
     * be�ll�tja az anyag �r�t.
     *
     * @param ar megkapja az �rat
     */
    public void setAr(int ar) {
        this.ar = ar;
    }

    /**
     * megadja a m�rt�kegys�get.
     *
     * @return visszat�r a m�rt�kegys�ggel
     */
    public String getMertekegyseg() {
        return mertekegyseg;
    }

    /**
     * be�ll�tja a m�rt�kegys�get.
     *
     * @param mertekegyseg megkapja a m�rt�kegys�get
     */
    public void setMertekegyseg(String mertekegyseg) {
        this.mertekegyseg = mertekegyseg;
    }
}
