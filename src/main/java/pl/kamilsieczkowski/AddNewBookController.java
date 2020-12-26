package pl.kamilsieczkowski;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Texts.*;

public class AddNewBookController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Button insertButton;

    @FXML
    private Button endButton;

    @FXML
    private Label signatureLabel;

    @FXML
    private Label id_numberLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label keyWordsLabel;

    @FXML
    private Label yearOfRelaseLabel;

    @FXML
    private Label tomeLabel;

    @FXML
    private Label valueLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label topicLabel;

    @FXML
    private Label localizationLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private Label dateOfAcquisitionLabel;

    @FXML
    private TextField signatureTextField;

    @FXML
    private TextField id_numberTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField isbnTextField;

    @FXML
    private TextField keywordsTextField;

    @FXML
    private TextField yearOfRelaseTextField;

    @FXML
    private TextField tomeTextField;

    @FXML
    private TextField valueTextField;

    @FXML
    private TextField publisherTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private SplitMenuButton topicMenuButton;

    @FXML
    private SplitMenuButton localizationMenuBotton;

    @FXML
    private SplitMenuButton typeMenuButton;

    @FXML
    private SplitMenuButton stateMenuButton;

    @FXML
    private DatePicker calendar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setWindowText();
    }


    private void setWindowText() {
        insertButton.setText(INSERT);
        endButton.setText(END);
        signatureLabel.setText(SIGNATURE);
        id_numberLabel.setText(ID_NUMBER);
        authorLabel.setText(AUTHOR);
        titleLabel.setText(TITLE);
        isbnLabel.setText(ISBN);
        keyWordsLabel.setText(KEY_WORDS);
        yearOfRelaseLabel.setText(YEAR_OF_REALASE);
        tomeLabel.setText(TOME);
        valueLabel.setText(VALUE);
        publisherLabel.setText(PUBLISHER);
        descriptionLabel.setText(DESCRIPTION);
        topicLabel.setText(TOPIC);
        localizationLabel.setText(LOCALIZATION);
        typeLabel.setText(TOPIC);
        stateLabel.setText(STATE);
        dateOfAcquisitionLabel.setText(DATE_OF_AQUISITION);
    }
}
