import controllers.CoreController;
import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.EventNote;
import models.Schedule;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import views.RootView;

import java.io.IOException;

public class MainApplication extends Application {
    private Stage primaryStage;
    private CoreController controller;
    private RootView mainView;
    private String title = "Calendar";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        ApplicationContext bf = new ClassPathXmlApplicationContext("config.xml");
        this.primaryStage = primaryStage;
        this.controller = (CoreController) bf.getBean("core-controller");
        this.controller.start();
        this.title = (String) bf.getBean("app-title");
        initRoot();
    }

    private void initRoot() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainView.fxml"));
            Pane mainLayout = loader.load();
            mainView = loader.getController();
            mainView.setController(controller);

            Scene sc = new Scene(mainLayout);
            primaryStage.setScene(sc);
//            primaryStage.setResizable(false);
            primaryStage.show();
            primaryStage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
