package lk.ijse.roombooking.controller.facilitycontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.FacilityBO;
import lk.ijse.roombooking.business.custom.SignUpBO;
import lk.ijse.roombooking.model.FacilityDTO;
import lk.ijse.roombooking.model.SignUpDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class FacilitiesController implements Initializable {

    @FXML
    private JFXTextField searchFacilityText;

    @FXML
    private JFXButton addFacilitiesButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXTextField facilityIdText;

    @FXML
    private JFXTextField descriptionText;

    @FXML
    private JFXTextField unitPriceText;

    @FXML
    private TableView<FacilityDTO> facilityTable;

    private ObservableList<FacilityDTO> data;

    private FacilityBO facilityBO;
    private SignUpBO signUpBO;

    public FacilitiesController() {
        facilityBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.FACILITY);
        signUpBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SIGNUP);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadColumns();
        loadFacilities();
    }

    private void loadColumns() {
        facilityTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("facilityId"));
        facilityTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        facilityTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("facilityPrice"));
    }

    private void loadFacilities() {
        try {
            ArrayList<FacilityDTO> facilityList = facilityBO.getAllFacilities();
            data = FXCollections.observableArrayList(facilityList);
            facilityTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addFacilitiesAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/facility/addFacility.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void facilitySearchKeyReleased() {
        searchFacilityText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchFacilityText.textProperty().get().isEmpty()) {
                    facilityTable.setItems(data);
                    return;
                }
                ObservableList<FacilityDTO> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<FacilityDTO, ?>> cols = facilityTable.getColumns();
                for (int a = 0; a < data.size(); a++) {
                    for (int b = 0; b < cols.size(); b++) {
                        TableColumn cola = cols.get(b);
                        String cellValues = cola.getCellData(data.get(a)).toString();
                        cellValues = cellValues.toLowerCase();
                        if (cellValues.contains(searchFacilityText.textProperty().get().toLowerCase())) {
                            tableItems.add(data.get(a));
                            break;
                        }
                    }
                }
                facilityTable.setItems(tableItems);
            }
        });
    }

    @FXML
    private void facilityTableClicked() {
        enableControls();
        FacilityDTO selectedItem = facilityTable.getSelectionModel().getSelectedItem();
        facilityIdText.setText(selectedItem.getFacilityId() + "");
        descriptionText.setText(selectedItem.getDescription());
        unitPriceText.setText(selectedItem.getFacilityPrice() + "");
        descriptionText.requestFocus();
    }

    private void enableControls() {
        descriptionText.setDisable(false);
        unitPriceText.setDisable(false);
        updateButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    @FXML
    private void updateButtonAction(ActionEvent event) {
        try {
            SignUpDTO admin = signUpBO.getAdmin();

            TextInputDialog d = new TextInputDialog();
            d.setTitle("Input Administrator Password");
            d.setHeaderText(null);
            d.setContentText("Password :");
            Optional<String> result = d.showAndWait();

            if (result.isPresent()) {
                if (result.get().equals(admin.getPassword())) {
                    boolean isUpdated = facilityBO.updateFacility(new FacilityDTO(Integer.parseInt(facilityIdText.getText()), descriptionText.getText(), new BigDecimal(unitPriceText.getText())));
                    if (isUpdated) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Facility Updated", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        loadFacilities();
                        disableControls();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Administrator Password Incorrect!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disableControls() {
        facilityIdText.setText(null);
        descriptionText.setText(null);
        unitPriceText.setText(null);

        descriptionText.setDisable(true);
        unitPriceText.setDisable(true);
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) {
        try {
            SignUpDTO admin = signUpBO.getAdmin();

            TextInputDialog d = new TextInputDialog();
            d.setTitle("Input Administrator Password");
            d.setHeaderText(null);
            d.setContentText("Password :");
            Optional<String> result = d.showAndWait();

            if (result.isPresent()) {
                if (result.get().equals(admin.getPassword())) {
                    boolean isDeleted = facilityBO.deleteFacility(Integer.parseInt(facilityIdText.getText()));
                    if (isDeleted) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Facility Deleted", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        loadFacilities();
                        disableControls();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Administrator Password Incorrect!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void descriptionAction(ActionEvent event) {
        unitPriceText.requestFocus();
    }

    @FXML
    void unitPriceAction(ActionEvent event) {
        updateButtonAction(event);
    }
}
