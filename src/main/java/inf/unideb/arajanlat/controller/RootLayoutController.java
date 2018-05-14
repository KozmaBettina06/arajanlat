package inf.unideb.arajanlat.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import inf.unideb.arajanlat.MainApp;
/**
 * Created by Stefyy on 2017.12.30..
 */
/**
 * a rootlaout controller osztálya.
 *
 */
public class RootLayoutController {

    @FXML
    private MenuBar menuBar;

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
    private void initialize()
    {
//        menuBar.getMenus().get(0).setOnAction(event -> {
//            JpaService.getJpaServiceInstance().getEntityManager().close();
//            JpaService.getJpaServiceInstance().getEntityManagerFactory().close();
//            System.exit(0);
//        });

        menuBar.getMenus().get(1).setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Program");
            alert.setHeaderText("A programot keszitette : Kozma Bettina");
            alert.setContentText("Ha hiba van, keress meg.");
            alert.initOwner(mainApp.getPrimaryStage());
            alert.showAndWait();
        });
    }
}
