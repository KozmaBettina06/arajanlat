package inf.unideb.arajanlat;

import inf.unideb.arajanlat.controller.*;
import inf.unideb.arajanlat.model.Arajanlat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;
/**
 * Created by Stefyy on 2017.12.30..
 */
public class MainApp extends Application {

    Logger logger = LoggerFactory.getLogger(MainApp.class);
    private Stage primaryStage;
    private BorderPane rootLayout;

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void init() throws Exception {
        logger.info("Ez az elso logom teszt");
        JpaService.getJpaServiceInstance().setEntityManagerFactory(Persistence.createEntityManagerFactory("ArajanlatProgram"));
        JpaService.getJpaServiceInstance().setEntityManager(JpaService.getJpaServiceInstance().getEntityManagerFactory().createEntityManager());
        JpaService.getJpaServiceInstance().setAnyagokServiceJPA(new AnyagokServiceJPA(JpaService.getJpaServiceInstance().getEntityManager()));
        JpaService.getJpaServiceInstance().setArajanlatServiceJPA(new ArajanlatServiceJPA(JpaService.getJpaServiceInstance().getEntityManager()));
    }

    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        initRootLayout();
        showValasztas();

    }

    public void initRootLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showValasztas()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Valasztas.fxml"));
            AnchorPane centerView = (AnchorPane) loader.load();
            this.primaryStage.setMaximized(true);
            rootLayout.setCenter(centerView);
            ValasztasController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAnyagFelvetel()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AnyagFelvetel.fxml"));
            AnchorPane centerView = (AnchorPane) loader.load();
            this.primaryStage.setMaximized(true);
            rootLayout.setCenter(centerView);
            AnyagFelvetelController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAnyagListaKeszites()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AnyagListaKeszites.fxml"));
            AnchorPane centerView = (AnchorPane) loader.load();
            this.primaryStage.setMaximized(true);
            rootLayout.setCenter(centerView);
            AnyagListaKeszitesController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showArajanlatSzerkesztes(List<Arajanlat> list)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AnyagListaKeszites.fxml"));
            AnchorPane centerView = (AnchorPane) loader.load();
            this.primaryStage.setMaximized(true);
            rootLayout.setCenter(centerView);
            AnyagListaKeszitesController controller = loader.getController();
            controller.setMainAppWithArajanlat(this,list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRegiSzerkesztesView()
    {
        try {            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/RegiSzerkesztes.fxml"));
            AnchorPane centerView = (AnchorPane) loader.load();
            this.primaryStage.setMaximized(true);
            rootLayout.setCenter(centerView);
            RegiSzerkesztesController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void stop() throws Exception {
        JpaService.getJpaServiceInstance().getEntityManager().close();
        JpaService.getJpaServiceInstance().getEntityManagerFactory().close();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
