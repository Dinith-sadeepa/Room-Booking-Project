package lk.ijse.roombooking.controller.servicecontroller;

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
import lk.ijse.roombooking.business.custom.ServiceBO;
import lk.ijse.roombooking.model.ServiceDTO;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChangePriceOfServicesController implements Initializable {

    @FXML
    private TableView<ServiceDTO> serviceTable;

    @FXML
    private ImageView closeImage;

    @FXML
    private JFXButton changeButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXTextField serviceIdText;

    @FXML
    private JFXTextField servicePriceText;

    @FXML
    private JFXTextField searchText;

    private ObservableList<ServiceDTO> data;

    private ServiceBO serviceBO;

    public ChangePriceOfServicesController() {
        serviceBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SERVICE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadColumns();
        loadServices();
    }

    private void loadColumns() {
        serviceTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        serviceTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        serviceTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
    }

    private void loadServices() {
        try {
            ArrayList<ServiceDTO> serviceDTOS = serviceBO.getAllService();
            data = FXCollections.observableArrayList(serviceDTOS);
            serviceTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void serviceTableClicked() {
        ServiceDTO selectedItem = serviceTable.getSelectionModel().getSelectedItem();
        enableControls();
        serviceIdText.setText(selectedItem.getServiceId() + "");
        servicePriceText.setText(selectedItem.getServicePrice() + "");
        servicePriceText.requestFocus();
    }

    private void enableControls() {
        servicePriceText.setDisable(false);
        changeButton.setDisable(false);
        cancelButton.setDisable(false);
    }

    @FXML
    private void changeButtonAction(ActionEvent event) {
        ServiceDTO selectedItem = serviceTable.getSelectionModel().getSelectedItem();
        try {
            boolean isChangeed = serviceBO.updateService(new ServiceDTO(selectedItem.getServiceId(), selectedItem.getDescription(), new BigDecimal(servicePriceText.getText())));
            if (isChangeed) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Service Price Changed", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                loadServices();
                disableControls();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disableControls() {
        serviceIdText.setText(null);
        servicePriceText.setText(null);

        servicePriceText.setDisable(true);
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
    private void serviceSearchKeyReleased() {
        searchText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchText.textProperty().get().isEmpty()) {
                    serviceTable.setItems(data);
                    return;
                }
                ObservableList<ServiceDTO> tableItem = FXCollections.observableArrayList();
                ObservableList<TableColumn<ServiceDTO, ?>> cols = serviceTable.getColumns();

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
                serviceTable.setItems(tableItem);
            }
        });
    }
}
