import controllers.MainController;

public class StartApp {

    public static void main(String[] args) {
//        MainApplication app = new MainApplication();
//        Application.launch(MainApplication.class);
        MainController controller = new MainController();
        controller.start();
    }
}
