package inf.unideb.arajanlat.controller;

import inf.unideb.arajanlat.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Stefyy on 2017.12.30..
 */
public class ValasztasController {

    @FXML
    private Label label1;
    /**
     * beállítja a mainApp-ot.
     *
     * @param mainApp megkapja a mainapp-ot
     */
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp=mainApp;
    }

    @FXML
    private void buttonTeszt(){
        this.mainApp.showAnyagFelvetel();
    }

    @FXML
    private void ujArajanlatButton(){
        this.mainApp.showAnyagListaKeszites();
    }

    @FXML
    private void showSzerkesztes(){
        this.mainApp.showRegiSzerkesztesView();
    }
}
