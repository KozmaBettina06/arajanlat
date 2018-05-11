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
/**
 * Created by Stefyy on 2017.12.30..
 */

/**
 * az anyagfelvétel ûrlap viewcontrollere.
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
        if (validateFields()) {
            Anyagok anyagok = getAnyag();

            JpaService.getJpaServiceInstance().getAnyagokServiceJPA().ujAnyagLetrehozasa(anyagok);

            showInformationAlert();

            resetFields();

        } else {
            showErrorAlert();
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

    private boolean validateFields() {
        if (megnevezesTextField.getText().equals("") || megnevezesTextField.getText().trim().equals("")) {
            return false;
        }

        if (arMennyisegTextField.getText().equals("") || arMennyisegTextField.getText().trim().equals("")) {
            return false;
        }

        if (egysegTextField.getText().equals("") || egysegTextField.getText().trim().equals("")) {
            return false;
        }

        if (mertekegysegSplitMenuButton.getText().equals("mertekegyseg")) {
            return false;
        }

        if (kategoriaSplitMenuButton.getText().equals("kategoria")) {
            return false;
        }

        return true;
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

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nincs megfeleloen kitoltve");
        alert.setContentText("Rosszul adtal meg valamait kerlek javitsd");
        alert.initOwner(mainApp.getPrimaryStage());
        alert.showAndWait();
    }
}
