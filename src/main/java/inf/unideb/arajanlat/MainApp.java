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

/**
 * az alkalmazás Main függvényét tartalmzó osztáyl.
 * kiterjeszti  az Application osztályt, amelytől JavaFx projekt lesz.
 * felülírásra kerül az init a stop és a strart meódusokat.
 *
 */
public class MainApp extends Application {

    Logger logger = LoggerFactory.getLogger(MainApp.class);
    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * megadja a rootLayoutot.
     *
     * @return visszatér a rootlayouttal
     */
    public BorderPane getRootLayout() {
        return rootLayout;
    }

    /**
     * megadja a PrimaryStage-et.
     *
     * @return visszatér a PrimaryStage-el
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Inicializálja és felépíti az adatbázis kapcsolatot.
     *
     * @throws Exception kivétel
     */
    @Override
    public void init() throws Exception {
        logger.info("Adatbázis kapcsolat felépítése ... ");
        JpaService.getJpaServiceInstance().setEntityManagerFactory(Persistence.createEntityManagerFactory("ArajanlatProgram"));
        JpaService.getJpaServiceInstance().setEntityManager(JpaService.getJpaServiceInstance().getEntityManagerFactory().createEntityManager());
        JpaService.getJpaServiceInstance().setAnyagokServiceJPA(new AnyagokServiceJPA(JpaService.getJpaServiceInstance().getEntityManager()));
        JpaService.getJpaServiceInstance().setArajanlatServiceJPA(new ArajanlatServiceJPA(JpaService.getJpaServiceInstance().getEntityManager()));
        logger.info("Adatbázis kapcsolat létrehozva. ");
    }

    /**
     * a start metódus a JavaFx alkalmazás belépési pontja.
     * inicializálódik a RootLayout, majd rákerül a kezdőoldal scene-je.
     *
     * @param primaryStage megkapja a PrimaryStage értékét
     * @throws Exception kivétel
     */
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        initRootLayout();
        showValasztas();

    }

    /**
     * létrehozza a RootLayoutot.
     *
     */
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

    /**
     * az alkalmazás kezdő scene-je.
     *
     */
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

    /**
     * Anyagfelvétel scene-re váltás.
     *
     */
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

    /**
     * AnyagListaKészítés scene-re váltás.
     */
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

    /**
     * ArajanlatSzerkesztes scene-re váltás.
     * @param list régi árajánlatot vár
     */
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
    }/**
     * RegiSzerkesztes scene-re váltás.
     */

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


    /**
     * Az alkalmazás bezárásakor hívódik meg, felbontja az adatbázis kapcsolatot.
     *
     * @throws Exception kivétel
     */
    @Override
    public void stop() throws Exception {
        JpaService.getJpaServiceInstance().getEntityManager().close();
        JpaService.getJpaServiceInstance().getEntityManagerFactory().close();
        logger.info("Adatbázis kapcsolat felbontva.");
    }

    /**
     * az alkalmazás belépési pontja.
     *
     * @param args argumentum lista
     * @throws Exception kivétel
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
