package inf.unideb.arajanlat.controller;

import inf.unideb.arajanlat.AnyagokServiceJPA;
import inf.unideb.arajanlat.JpaService;
import inf.unideb.arajanlat.MainApp;
import inf.unideb.arajanlat.model.Anyagok;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by Stefyy on 2017.12.30..
 */

/**
 * az anyagfelvétel űrlap viewcontrollere.
 *
 */
public class AnyagFelvetelController {

    @FXML
    private TextField megnevezesTextField;

    @FXML
    private TextField arMennyisegTextField;

    @FXML
    private TextField egysegTextField;

    @FXML
    private SplitMenuButton mertekegysegSplitMenuButton;

    @FXML
    private SplitMenuButton kategoriaSplitMenuButton;

    Logger logger = LoggerFactory.getLogger(AnyagFelvetelController.class);
    private MainApp mainApp;



    /**
     * beállítja a mainApp-ot.
     *
     * @param mainApp megkapja a mainapp-ot
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void showValasztas() {
        this.mainApp.showValasztas();
    }

    @FXML
    private void anyagFeltoltesButton() {
        String hiba = hibaMessage();
        if (validateFields(hiba)) {
            Anyagok anyagok = getAnyag();

            JpaService.getJpaServiceInstance().getAnyagokServiceJPA().ujAnyagLetrehozasa(anyagok);

            showInformationAlert();
            logger.info("új anyag feltöltve az adatbázisba.");
            resetFields();

        } else {
            showErrorAlert(hiba);
            logger.info("Új anyag feltöltése nem sikerült, nem megfelelő kitöltés miatt.");
        }

    }

    @FXML
    private void mertekegysegValasztas(ActionEvent event) {
        mertekegysegSplitMenuButton.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    private void kategoriaValasztas(ActionEvent event) {
        kategoriaSplitMenuButton.setText(((MenuItem) event.getSource()).getText());
    }

    private String hibaMessage() {
        Integer elso;
        Integer masodik;
        String hiba="";

        try{
            elso=Integer.parseInt(arMennyisegTextField.getText());
        }catch (NumberFormatException e){
            hiba= hiba +"Az árhoz számot kell beírni.\n";
        }

        try{
            masodik=Integer.parseInt(egysegTextField.getText());
        }catch (NumberFormatException e){
            hiba= hiba +"a mennyiséghez számot kell beírni.\n";
        }
        if (megnevezesTextField.getText().equals("") || megnevezesTextField.getText().trim().equals("")) {
            hiba= hiba +"Megnevezés nincs kitöltve.\n";
        }

        if (arMennyisegTextField.getText().equals("") || arMennyisegTextField.getText().trim().equals("")) {
            hiba= hiba +"Ár nincs kitöltve.\n";
        }

        if (egysegTextField.getText().equals("") || egysegTextField.getText().trim().equals("")) {
            hiba= hiba +"Egység nincs kitöltve.\n";
        }

        if (mertekegysegSplitMenuButton.getText().equals("mertekegyseg")) {
            hiba= hiba +"mértékegység nincs kiválasztva.\n";
        }

        if (kategoriaSplitMenuButton.getText().equals("kategoria")) {
            hiba= hiba +"Kategria nincs kiválasztva.\n";
        }
        return hiba;
    }

    /**
     * Megvizsgálja, hogy létrejött-e hibaüzenet.
     *
     * @param message megkapja a hiba Stringet
     * @return logikai értékkel tér vissza
     */
    public boolean validateFields(String message){
        if(message.equals("")){
            return true;
        }else {
            return false;
        }
    }
    private void resetFields() {
        megnevezesTextField.setText("");
        arMennyisegTextField.setText("");
        egysegTextField.setText("");
        mertekegysegSplitMenuButton.setText("mertekegyseg");
        kategoriaSplitMenuButton.setText("kategoria");
    }

    private Anyagok getAnyag() {
        String megnevezes = megnevezesTextField.getText();
        String kategoria = kategoriaSplitMenuButton.getText();
        Integer egyseg = Integer.parseInt(egysegTextField.getText());
        Integer arMennyiseg = Integer.parseInt(arMennyisegTextField.getText());
        String mertekegyseg = mertekegysegSplitMenuButton.getText();
        Anyagok anyagok = new Anyagok(megnevezes, kategoria, egyseg, arMennyiseg, mertekegyseg);

        return anyagok;
    }

    private void showInformationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sikeres feltoltes");
        alert.setContentText("Sikeresen feltoltotted az adatbazisba");
        alert.initOwner(mainApp.getPrimaryStage());
        alert.showAndWait();
    }

    private void showErrorAlert(String hiba) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nincs megfeleloen kitoltve");
        alert.setContentText(hiba);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.showAndWait();
    }
}
