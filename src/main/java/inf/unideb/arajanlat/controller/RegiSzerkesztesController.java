package inf.unideb.arajanlat.controller;

import inf.unideb.arajanlat.JpaService;
import inf.unideb.arajanlat.MainApp;
import inf.unideb.arajanlat.model.Arajanlat;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefyy on 2018. 05. 07..
 */
public class RegiSzerkesztesController {

    @FXML
    private TableView<String> regiAjanlatokTableView;

    @FXML
    private TableColumn<String,String> regiAjanlatokTableColumn;

    List<Arajanlat> arajanlatList = new ArrayList<>();
    String kivalasztott = null;

    @FXML
    private void initialize() {
        List<String> regiAjanlatok = JpaService.getJpaServiceInstance().getArajanlatServiceJPA().osszesArajanlat();
        ObservableList<String> stringObservableList = FXCollections.observableList(regiAjanlatok);
        regiAjanlatokTableView.setItems(stringObservableList);
        regiAjanlatokTableColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));
        anyagTableViewKivalasztottChangeListener();
    }

    private MainApp mainApp;
    /**
     * beállítja a mainApp-ot.
     *
     * @param mainApp megkapja a mainapp-ot
     */
    public void setMainApp(MainApp mainApp){
        this.mainApp=mainApp;
    }


    @FXML
    private void showValasztasView(){
        this.mainApp.showValasztas();
    }

    @FXML
    private void showAjanlatKeszitoView(){
        arajanlatList = JpaService.getJpaServiceInstance().getArajanlatServiceJPA().arajanlatNevSzerint(kivalasztott);
        this.mainApp.showArajanlatSzerkesztes(arajanlatList);
    }


    private void anyagTableViewKivalasztottChangeListener() {
        regiAjanlatokTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                kivalasztott=newValue;
            }
        });
    }
}
