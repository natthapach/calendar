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
import views.RootView;

import java.io.IOException;

public class MainApplication extends Application {
    private Stage primaryStage;
    private CoreController controller;
    private RootView mainView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.controller = new MainController();
        this.controller.start();
        initRoot();
        showEvents();
    }

    private void showEvents() {
        Schedule schedule = controller.getSchedule();
        for (EventNote event : schedule.getAllEvents()) {
            BorderPane pane = new BorderPane();

        }
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
            primaryStage.setTitle("Calendar");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
