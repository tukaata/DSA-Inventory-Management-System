import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddProductInterfaceController implements Initializable {

    // All FXML Button, Field, RadioButton, Label Declaration
    // ... (Other FXML components)

    private IMSFXMLDocumentController documentController;
    private Product productSelected;
    private Part partSelected;
    private Boolean editData = false;
    private ObservableList<Part> productParts = FXCollections.observableArrayList();
    private ObservableList<Part> existingProductParts = FXCollections.observableArrayList();

    // ... (Other FXML components)

    @FXML
    void initialize() {
        // ... (Initialization of TableView and other components)
    }

    @FXML
    void productPartEditAction(ActionEvent event) {
        // ... (Implementation for modify action)
    }

    @FXML
    void productPartDeleteAction(ActionEvent event) {
        // ... (Implementation for delete action)
    }

    @FXML
    void productPartDeAssociateAction(ActionEvent event) {
        // ... (Implementation for de-associate action)
    }

    @FXML
    void closeButtonAction(ActionEvent event) {
        // ... (Implementation for close button)
    }

    @FXML
    private void addProductAssociatedPart(ActionEvent event) {
        // ... (Implementation for addProductAssociatedPart action)
    }

    @FXML
    void productSaveButtonAction(ActionEvent event) {
        // ... (Implementation for productSaveButtonAction)
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ... (Initialization of TableView and other components)
    }

    void setParentController(IMSFXMLDocumentController documentController) {
        this.documentController = documentController;
    }

    void setData(Product productSelected) {
        // ... (Implementation to set data when a product is selected for modification)
    }

    // Other helper methods for adding, updating, and setting data

    // ...
}
