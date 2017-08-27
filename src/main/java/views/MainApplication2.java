package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication2 extends Application {
    private Stage primaryStage;
    private AnchorPane mainLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
//        System.out.println(getClass().getResource("../MainView.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../MainView.fxml"));
        this.primaryStage.setTitle("Test");
        this.primaryStage.setResizable(false);
        this.primaryStage.setScene(new Scene(root, 420, 275));
        this.primaryStage.show();
//        initRoot();


    }

    private void initRoot() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("resources/MainView.fxml"));
            mainLayout = loader.load();

            Scene sc = new Scene(mainLayout);
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
