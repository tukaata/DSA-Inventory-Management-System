import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductPartInterfaceController implements Initializable {

    @FXML private JFXTextField partCompanyNameField;
    @FXML private JFXTextField partPriceField;
    @FXML private JFXTextField partMaxField;
    @FXML private JFXTextField partMinField;
    @FXML private JFXTextField partNameTextField;
    @FXML private JFXTextField partLnvField;
    @FXML private JFXTextField partIDTextField;
    @FXML private JFXTextField partMachineIDField;
    @FXML private JFXRadioButton inHouseButton;
    @FXML private JFXRadioButton outsourcedButton;
    @FXML private Label partCompanyNameLabel;
    @FXML private Label partMachineIDLabel;
    @FXML private JFXButton closeButton;

    // Parts Class object declaration for future use
    Part selectedPart;
    // Adding new data or editing previous data checker boolean variable
    Boolean editData = false, inHouse = true;
    // Parent class reference variable declaration
    private AddProductInterfaceController documentController;

    // ID of associated part, default -1 for individual parts
    int asProID = -1;

    // Close/Cancel Button on Add/Modify Window to return to main window
    @FXML
    void closeButtonAction(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    // this method is called upon outsource button press to disable company name and company label as required
    @FXML
    void outsourceButtonPress(ActionEvent event) {
        partCompanyNameField.setVisible(true);
        partCompanyNameLabel.setVisible(true);

        inHouseButton.setSelected(false);
        inHouse = false;
        outsourcedButton.setSelected(true);

        partMachineIDField.setVisible(false);
        partMachineIDLabel.setVisible(false);
    }

    // this method enables the company name and field for out-sourced parts
    @FXML
    void inHouseButtonAction(ActionEvent event) {
        partCompanyNameField.setVisible(false);
        partCompanyNameLabel.setVisible(false);

        outsourcedButton.setSelected(false);
        inHouse = true;
        inHouseButton.setSelected(true);

        partMachineIDField.setVisible(true);
        partMachineIDLabel.setVisible(true);
    }

    // This button action triggers the addition of new data or update of existing one by condition
    @FXML
    void partSaveButtonAction(ActionEvent event) {
        // Your save button logic here
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // Default parent controller class setter method
    void setParentController(AddProductInterfaceController documentController) {
        this.documentController = documentController;
    }

    void setID(int generateID) {
        partIDTextField.setText(String.valueOf(generateID));
    }

    void setAssociatedPart(int asProID) {
        this.asProID = asProID;
    }

    void setPart(Part partSelected) {
        partIDTextField.setText(String.valueOf(partSelected.getPartsID()));
        partNameTextField.setText(partSelected.getPartsName());
        partLnvField.setText(String.valueOf(partSelected.getPartsLevel()));
        partPriceField.setText(String.valueOf(partSelected.getPartsCost()));
        editData = true;
        partMaxField.setText(String.valueOf(partSelected.getPartMax()));
        partMinField.setText(String.valueOf(partSelected.getPartMin()));

        if (partSelected.inHouse) {
            inHouseButtonAction(null);
            partMachineIDField.setText(partSelected.getCompanyNameOrMachineID());
        } else {
            outsourceButtonPress(null);
            partCompanyNameField.setText(partSelected.getCompanyNameOrMachineID());
        }

        editData = true;
        selectedPart = partSelected;
    }
}
