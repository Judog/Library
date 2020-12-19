package pl.kamilsieczkowski;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Texts.LOGIN_WINDOW_TITLE;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/loginWindow.fxml"));
        primaryStage.setTitle(LOGIN_WINDOW_TITLE);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}
