package pl.kamilsieczkowski;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Texts.*;

public class LoginPopupController implements Initializable {
    @FXML
    private Tab publicationsTab;
    @FXML
    private Label id_numberLabel;
    @FXML
    private Label signatureLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label keyWordsLabel;
    @FXML
    private Button searchButton;
    @FXML
    private TableColumn idNumberColumn;
    @FXML
    private TableColumn signatureColumn;
    @FXML
    private TableColumn authorColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn tomeColumn;
    @FXML
    private TableColumn conditionColumn;
    @FXML
    private TableColumn editionColumn;
    @FXML
    private TableColumn localizationColumn;
    @FXML
    private Button borrowButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button prolongButton;
    @FXML
    private Button editDescriptionButton;
    @FXML
    private Button addNewButton;
    @FXML
    private Label foundLabel;
    @FXML
    private Label avabilityLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setWindowText();
    }

    private void setWindowText() {
        publicationsTab.setText(PUBLICATIONS);
        id_numberLabel.setText(ID_NUMBER);
        idNumberColumn.setText(ID_NUMBER);
        signatureLabel.setText(SIGNATURE);
        signatureColumn.setText(SIGNATURE);
        authorLabel.setText(AUTHOR);
        authorColumn.setText(AUTHOR);
        titleLabel.setText(TITLE);
        titleColumn.setText(TITLE);
        keyWordsLabel.setText(KEY_WORDS);
        searchButton.setText(SEARCH);
        tomeColumn.setText(TOME);
        conditionColumn.setText(CONDITION);
        editionColumn.setText(EDITION);
        localizationColumn.setText(LOCALIZATION);
        borrowButton.setText(BORROW);
        returnButton.setText(RETURN);
        prolongButton.setText(PROLONG);
        editDescriptionButton.setText(EDIT_DESCRIPTION);
        addNewButton.setText(ADD_NEW);
        foundLabel.setText(FOUND);
        avabilityLabel.setText(AVABILITY);
    }

}

