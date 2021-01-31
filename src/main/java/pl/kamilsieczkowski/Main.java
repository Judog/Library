package pl.kamilsieczkowski;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.kamilsieczkowski.utils.Window;

import static pl.kamilsieczkowski.constants.Texts.IN_LIBRARY;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Window window = new Window();
        window.openNewWindow("/loginWindow.fxml", IN_LIBRARY);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
