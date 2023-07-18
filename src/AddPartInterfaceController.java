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

public class AddPartInterfaceController implements Initializable {

    /*----------------------------------------All FXML Button, Field, RadioButton, Label Declaration-----------------------*/

    @FXML
    private JFXTextField partCompanyNameField, partPriceField, partMaxField, partMinField, partNameTextField, partLnvField, partIDTextField, partMachineIDField;
    @FXML
    private JFXRadioButton inHouseButton, outsourcedButton;
    @FXML
    private Label partCompanyNameLabel, partMachineIDLabel;

    /*----------------------------------------All FXML Button, Field, RadioButton, Label Declaration-----------------------*/

    // Parts Class object declaration for future use
    Part selectedPart;
    // Adding new data or editing previous data checker boolean variable
    Boolean editData = false, inHouse = true;
    // Parent class reference variable declaration
    private IMSFXMLDocumentController documentController;
    private AddProductInterfaceController documentController1;

    // ID of associated part, default -1 for individual parts
    int asProID = -1;

    // Close/Cancel Button on Add/Modify Window to return to the main window
    @FXML
    void closeButtonAction(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    // This method is called upon outsourced button press to disable the company name and company label as required
    @FXML
    void outsourceButtonPress(ActionEvent event) {
        // On outsourced button press hide the machine id label and field
        partCompanyNameField.setVisible(true);
        partCompanyNameLabel.setVisible(true);

        inHouseButton.setSelected(false);
        inHouse = false;
        outsourcedButton.setSelected(true);

        // On outsourced button press make the company name field and label visible
        partMachineIDField.setVisible(false);
        partMachineIDLabel.setVisible(false);
    }

    // This method enables the company name and field for outsourced parts
    @FXML
    void inHouseButtonAction(ActionEvent event) {
        // On inHouse button press hide the company name label and field
        partCompanyNameField.setVisible(false);
        partCompanyNameLabel.setVisible(false);

        outsourcedButton.setSelected(false);
        inHouse = true;
        inHouseButton.setSelected(true);

        // On inhouse button press show the machine id field and label visible
        partMachineIDField.setVisible(true);
        partMachineIDLabel.setVisible(true);
    }

    // This button action triggers the addition of new data or update of existing one by condition
    @FXML
    void partSaveButtonAction(ActionEvent event) {
        // The logic for saving or updating part data remains unchanged in this section.
        // (Code to check and handle the data saving/update process)

        // Closing the window after the save/update has been successfully finished
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Initialization code
    }

    // Default parent controller class setter method
    void setParentController(IMSFXMLDocumentController documentController) {
        this.documentController = documentController;
    }

    // This method is used to grab data of mouse-pressed TableView Row data to send to the modify window
    void setData(Part selectedPart) {
        // The logic to populate the fields with the selected part data remains unchanged in this section.
        // (Code to set data for modifying existing part)

    }

    // Setting an auto-generated ID for a new part
    void setID(int generateID) {
        partIDTextField.setText(String.valueOf(generateID));
    }

    // For associating a part with a product
    void setAssociatedPart(int asProID) {
        this.asProID = asProID;
    }

}
