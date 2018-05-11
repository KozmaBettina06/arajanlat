package inf.unideb.arajanlat.controller;

import inf.unideb.arajanlat.ArajanlatServiceJPA;
import inf.unideb.arajanlat.JpaService;
import inf.unideb.arajanlat.MainApp;
import inf.unideb.arajanlat.model.Anyagok;
import inf.unideb.arajanlat.model.Arajanlat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefyy on 2018. 05. 07..
 */
public class AnyagListaKeszitesController {

    @FXML
    private SplitMenuButton kategoriaSplitMenuButton;

    @FXML
    private TextField mennyisegTextField;

    @FXML
    private TextField osszegTextField;

    @FXML
    private TextField ajanlatNeveTextField;

    @FXML
    private TableView<Anyagok> anyagokTableView;

    @FXML
    private TableColumn<Anyagok,String> anyagNeveTableColumn;

    @FXML
    private TableColumn<Anyagok,Integer> egysegTableColumn;

    @FXML
    private TableColumn<Anyagok,Integer> arTableColumn;

    @FXML
    private TableColumn<Anyagok,String> mertekegysegTableColumn;

    @FXML
    private TableView<Arajanlat> veglegesitettAnyagokTableView;

    @FXML
    private TableColumn<Arajanlat,String> veglegesitettAnyagNeveTableColumn;

    @FXML
    private TableColumn<Arajanlat,Integer> veglegesitettEgysegTableColumn;

    @FXML
    private TableColumn<Arajanlat,Integer> veglegesitettMennyisegTableColumn;

    @FXML
    private TableColumn<Arajanlat,Integer> veglegesitettArTableColumn;

    @FXML
    private TableColumn<Arajanlat,String> veglegesitettMertekegysegTableColumn;

    Anyagok akatualisAnyag = null;
    Arajanlat veglegesitettAktualisAnyag = null;

    List<Arajanlat> arajanlatList = new ArrayList<>();

    @FXML
    private void initialize() {
        anyagTableViewKivalasztottChangeListener();
        veglegesitettAnyagTableViewKivalasztottChangeListener();
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

    /**
     * kitölti a régi árajánlattal a táblát és kiszámolja az értéket, lehet  szerkesztei.
     *
     * @param mainApp mainapp
     * @param list lista
     */
    public void setMainAppWithArajanlat(MainApp mainApp,List<Arajanlat> list){
        this.mainApp=mainApp;
        szerkesztettRegiLista(list);
        showVeglegesitettTable();
        oszzegKiszamitas();
    }

    @FXML
    private void showKezdolap(){
        this.mainApp.showValasztas();
    }

    @FXML
    private void arajanlatFeltoltes(){
        if(ajanlatNeveTextField.getText().trim().equals("")){
            return;
        }

        for(Arajanlat all : arajanlatList){
            all.setArajanlatNeve(ajanlatNeveTextField.getText());
        }

        JpaService.getJpaServiceInstance().getArajanlatServiceJPA().ujArajanlatLetrehozasa(arajanlatList);

    }


    @FXML
    private void keresesButton(){
        List<Anyagok> anyagokList = JpaService.getJpaServiceInstance().getAnyagokServiceJPA().anyagokKategoriaSzerint(kategoriaSplitMenuButton.getText());

        showTableColumns(anyagokList);
    }

    @FXML
    private void kategoriaValasztas(ActionEvent event) {
        kategoriaSplitMenuButton.setText(((MenuItem) event.getSource()).getText());
    }

    private void showTableColumns(List<Anyagok> anyagok){
        ObservableList<Anyagok> observableList = FXCollections.observableList(anyagok);
        anyagokTableView.setItems(observableList);
        anyagNeveTableColumn.setCellValueFactory(new PropertyValueFactory<Anyagok, String>("anyagNeve"));
        egysegTableColumn.setCellValueFactory(new PropertyValueFactory<Anyagok, Integer>("egyseg"));
        arTableColumn.setCellValueFactory(new PropertyValueFactory<Anyagok, Integer>("ar"));
        mertekegysegTableColumn.setCellValueFactory(new PropertyValueFactory<Anyagok, String>("mertekegyseg"));
    }


    private void anyagTableViewKivalasztottChangeListener(){
        anyagokTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Anyagok>() {
            @Override
            public void changed(ObservableValue<? extends Anyagok> observable, Anyagok oldValue, Anyagok newValue) {
                if (newValue == null) {
                    return;
                } else {
                    akatualisAnyag = newValue;
                }
            }
        });
    }

    private void veglegesitettAnyagTableViewKivalasztottChangeListener() {
        veglegesitettAnyagokTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Arajanlat>() {
            @Override
            public void changed(ObservableValue<? extends Arajanlat> observable, Arajanlat oldValue, Arajanlat newValue) {
                if (newValue == null) {
                    return;
                } else {
                    veglegesitettAktualisAnyag = newValue;
                }
            }
        });
    }

    @FXML
    private void showVeglegeitettTableColumns(){
        addNewArajanlatList();
        oszzegKiszamitas();
        showVeglegesitettTable();
    }

    @FXML
    private void removeVeglegesitettAnyag(){
        if(arajanlatList.size() == 0){
            return;
        }

        arajanlatList.remove(veglegesitettAktualisAnyag);
        oszzegKiszamitas();
        showVeglegesitettTable();
    }


    private void addNewArajanlatList(){
        Arajanlat newArajanlat = new Arajanlat();
        newArajanlat.setAnyagNeve(akatualisAnyag.getAnyagNeve());
        newArajanlat.setEgyseg(akatualisAnyag.getEgyseg());
        //Validation hianyzik
        newArajanlat.setMennyiseg(Integer.parseInt(mennyisegTextField.getText()));
        newArajanlat.setEgysegar(akatualisAnyag.getAr() * newArajanlat.getMennyiseg());
        newArajanlat.setMertekegyseg(akatualisAnyag.getMertekegyseg());
        newArajanlat.setKategoria(akatualisAnyag.getKategoria());

        for(Arajanlat all : arajanlatList){
            if(all.getAnyagNeve().contains(newArajanlat.getAnyagNeve())){
                return;
            }
        }
        arajanlatList.add(newArajanlat);
    }

    private void showVeglegesitettTable(){
        ObservableList<Arajanlat> observableList = FXCollections.observableList(arajanlatList);
        veglegesitettAnyagokTableView.setItems(observableList);
        veglegesitettAnyagNeveTableColumn.setCellValueFactory(new PropertyValueFactory<Arajanlat, String>("anyagNeve"));
        veglegesitettEgysegTableColumn.setCellValueFactory(new PropertyValueFactory<Arajanlat, Integer>("egyseg"));
        veglegesitettArTableColumn.setCellValueFactory(new PropertyValueFactory<Arajanlat, Integer>("egysegar"));
        veglegesitettMertekegysegTableColumn.setCellValueFactory(new PropertyValueFactory<Arajanlat, String>("mertekegyseg"));
        veglegesitettMennyisegTableColumn.setCellValueFactory(new PropertyValueFactory<Arajanlat, Integer>("mennyiseg"));
    }

    @FXML
    private void oszzegKiszamitas(){
        int osszeg=0;
        for(Arajanlat all : arajanlatList){
            osszeg += all.getEgysegar();
        }

        osszegTextField.setText(Integer.toString(osszeg));
    }


    private void szerkesztettRegiLista(List<Arajanlat> list){
        for(int i=0;i<list.size();i++){
            Arajanlat uj = new Arajanlat(list.get(i));
            arajanlatList.add(uj);
        }
    }
}

