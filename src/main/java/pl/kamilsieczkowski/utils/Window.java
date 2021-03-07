package pl.kamilsieczkowski.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Window {
    public static final Logger LOG = LogManager.getLogger(Window.class);

    public Stage getWindow(Pane pane) {
        return (Stage) pane.getScene().getWindow();
    }

    public void openNewWindow(String windowSource, String windowTitle) {
        Stage stage = new Stage();
        Parent content = getWindowContent(windowSource);
        Scene scene = new Scene(content);
        stage.setTitle(windowTitle);
        stage.setScene(scene);
        stage.show();
    }

    private Parent getWindowContent(String windowSource) {
        Parent content = null;
        try {
            content = FXMLLoader.load(getClass().getResource(windowSource));
        } catch (IOException e) {
            LOG.error("can't get Window resource", e);
        }
        return content;
    }

    public void closeWindow(Pane pane) {
        getWindow(pane).close();
    }

    public void changeWindow(Pane pane, String windowSource) {
        Parent windowToChange = null;
        try {
            windowToChange = FXMLLoader.load(getClass().getResource(windowSource));
        } catch (IOException e) {
            LOG.error("can't get Window resource", e);
        }
        pane.getChildren().setAll(windowToChange);
    }
}
