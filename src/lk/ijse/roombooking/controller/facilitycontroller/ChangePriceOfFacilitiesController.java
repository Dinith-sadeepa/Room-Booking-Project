package lk.ijse.roombooking.controller.facilitycontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.FacilityBO;
import lk.ijse.roombooking.model.FacilityDTO;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChangePriceOfFacilitiesController implements Initializable {

    @FXML
    private TableView<FacilityDTO> facilityTable;

    @FXML
    private ImageView closeImage;

    @FXML
    private JFXButton changeButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXTextField facilityIdText;

    @FXML
    private JFXTextField facilityPriceText;

    @FXML
    private JFXTextField searchText;

    private FacilityBO facilityBO;

    private ObservableList<FacilityDTO> data;

    public ChangePriceOfFacilitiesController() {
        facilityBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.FACILITY);
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
    private void facilityTableClicked() {
        FacilityDTO selectedItem = facilityTable.getSelectionModel().getSelectedItem();
        enableControls();
        facilityIdText.setText(selectedItem.getFacilityId() + "");
        facilityPriceText.setText(selectedItem.getFacilityPrice() + "");
        facilityPriceText.requestFocus();
    }

    private void enableControls() {
        facilityPriceText.setDisable(false);
        changeButton.setDisable(false);
        cancelButton.setDisable(false);
    }

    @FXML
    private void changeButtonAction(ActionEvent event) {
        FacilityDTO selectedItem = facilityTable.getSelectionModel().getSelectedItem();
        try {
            boolean isChangeed = facilityBO.updateFacility(new FacilityDTO(selectedItem.getFacilityId(), selectedItem.getDescription(), new BigDecimal(facilityPriceText.getText())));
            if (isChangeed) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Facility Price Changed", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                loadFacilities();
                disableControls();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disableControls() {
        facilityIdText.setText(null);
        facilityPriceText.setText(null);

        facilityPriceText.setDisable(true);
        changeButton.setDisable(true);
        cancelButton.setDisable(true);
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        disableControls();
    }

    @FXML
    private void closeImageClicked() {
        Stage stage = (Stage) closeImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void facilitySearchKeyReleased() {
        searchText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchText.textProperty().get().isEmpty()) {
                    facilityTable.setItems(data);
                    return;
                }
                ObservableList<FacilityDTO> tableItem = FXCollections.observableArrayList();
                ObservableList<TableColumn<FacilityDTO, ?>> cols = facilityTable.getColumns();

                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(data.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if (cellValue.contains(searchText.textProperty().get().toLowerCase())) {
                            tableItem.add(data.get(i));
                            break;
                        }
                    }
                }
                facilityTable.setItems(tableItem);
            }
        });
    }
}
