import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class IMSFXMLDocumentController implements Initializable {

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productID, productLevel;
    @FXML
    private TableColumn<Product, Double> productCost;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private JFXTextField productFilterString;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partsID, partsLevel;
    @FXML
    private TableColumn<Part, Double> partsCost;
    @FXML
    private TableColumn<Part, String> partsName;
    @FXML
    private JFXTextField partsFilterString;

    private ObservableList<Part> parts = FXCollections.observableArrayList();
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private Part partSelected = null;
    private Product productSelected = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupProductTable();
        setupPartsTable();
    }

    private void setupProductTable() {
        productTable.getItems().setAll(products);
        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productLevel.setCellValueFactory(new PropertyValueFactory<>("productLevel"));
        productCost.setCellValueFactory(new PropertyValueFactory<>("productCost"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productTable.setOnMousePressed(event -> productSelected = productTable.getSelectionModel().getSelectedItem());
    }

    private void setupPartsTable() {
        partsTable.getItems().setAll(parts);
        partsID.setCellValueFactory(new PropertyValueFactory<>("partsID"));
        partsLevel.setCellValueFactory(new PropertyValueFactory<>("partsLevel"));
        partsCost.setCellValueFactory(new PropertyValueFactory<>("partsCost"));
        partsName.setCellValueFactory(new PropertyValueFactory<>("partsName"));
        partsTable.setOnMousePressed(event -> partSelected = partsTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void partAddButtonAction(ActionEvent event) {
        openAddPartInterface();
    }

    private void openAddPartInterface() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPartInterface.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Part Window");
            stage.setScene(new Scene(root));
            loader.<AddPartInterfaceController>getController().setParentController(this);
            AddPartInterfaceController api = loader.getController();
            api.setID(generatePartsID());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(IMSFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void partsModifyButtonAction(ActionEvent event) {
        if (partSelected != null) {
            openModifyPartInterface(partSelected);
        } else {
            showPartSelectionWarning();
        }
    }

    private void openModifyPartInterface(Part selectedPart) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPartInterface.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Modify Part Window");
            stage.setScene(new Scene(root));
            loader.<AddPartInterfaceController>getController().setParentController(this);
            AddPartInterfaceController api = loader.getController();
            api.setData(selectedPart);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(IMSFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void deletePartsAction(ActionEvent event) {
        if (partSelected != null && partSelected.getAssociatedPartID() == -1) {
            showPartDeletionConfirmation();
        } else if (partSelected != null && partSelected.getAssociatedPartID() != -1) {
            openModifyProductInterfaceForPartDeletion(partSelected);
        } else {
            showPartSelectionWarning();
        }
    }

    private void showPartDeletionConfirmation() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Caution!");
        alert.setHeaderText("Are you sure you want to delete Part ID: " + partSelected.getPartsID() + "?");
        alert.setContentText(null);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            deletePartFromTable(partSelected);
        }
    }

    private void deletePartFromTable(Part selectedPart) {
        partsTable.getItems().remove(selectedPart);
        parts.remove(selectedPart);
    }

    private void openModifyProductInterfaceForPartDeletion(Part selectedPart) {
        // ...
    }

    private void showPartSelectionWarning() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error!");
        alert.setHeaderText("Please, Select at least one part to perform the delete operation!");
        alert.setContentText(null);
        alert.showAndWait();
    }

    @FXML
    void partsSearchButtonAction(ActionEvent event) {
        FilteredList<Part> filteredData = new FilteredList<>(parts, p -> true);
        filteredData.setPredicate(Part -> {
            String lowerCaseFilter = partsFilterString.getText().toLowerCase();
            return Part.getPartsName().toLowerCase().contains(lowerCaseFilter);
        });

        SortedList<Part> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(partsTable.comparatorProperty());
        partsTable.setItems(sortedData);
    }

    @FXML
    void productAddButtonAction(ActionEvent event) {
        openAddProductInterface();
    }

    private void openAddProductInterface() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProductInterface.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Product Window");
            stage.setScene(new Scene(root));
            loader.<AddProductInterfaceController>getController().setParentController(this);
            AddProductInterfaceController api = loader.getController();
            api.setData(generateProductsID());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(IMSFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void productModifyButtonAction(ActionEvent event) {
        if (productSelected != null) {
            openModifyProductInterface(productSelected);
        } else {
            showProductSelectionWarning();
        }
