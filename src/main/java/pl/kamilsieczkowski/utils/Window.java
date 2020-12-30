package pl.kamilsieczkowski.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Window {
    public Stage getWindow(Pane pane) {
        return (Stage) pane.getScene().getWindow();
    }

    public void openLibraryWindow(String windowSource, String windowTitle) throws IOException {
        Pane window = new Pane();
        Stage stage = new Stage();
        Parent content = FXMLLoader.load(getClass().getResource(windowSource));
        Scene scene = new Scene(content);
        stage.setTitle(windowTitle);
        stage.setScene(scene);
        stage.show();
    }
}
