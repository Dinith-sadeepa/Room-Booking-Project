package lk.ijse.roombooking.controller.servicecontroller;

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
import lk.ijse.roombooking.business.custom.ServiceBO;
import lk.ijse.roombooking.business.custom.SignUpBO;
import lk.ijse.roombooking.model.ServiceDTO;
import lk.ijse.roombooking.model.SignUpDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ServicesController implements Initializable {

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXTextField serviceIdText;

    @FXML
    private JFXTextField descriptionText;

    @FXML
    private JFXTextField unitPriceText;

    @FXML
    private TableView<ServiceDTO> serviceTable;

    private ServiceBO serviceBO;

    private ObservableList<ServiceDTO> data;

    @FXML
    private JFXTextField serviceSearchText;

    private SignUpBO signUpBO;

    public ServicesController() {
        serviceBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SERVICE);
        signUpBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SIGNUP);
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
    private void serviceSearchKeyReleased() {
        serviceSearchText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (serviceSearchText.textProperty().get().isEmpty()) {
                    serviceTable.setItems(data);
                    return;
                }
                ObservableList<ServiceDTO> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<ServiceDTO, ?>> cols = serviceTable.getColumns();
                for (int a = 0; a < data.size(); a++) {
                    for (int b = 0; b < cols.size(); b++) {
                        TableColumn cola = cols.get(b);
                        String cellValues = cola.getCellData(data.get(a)).toString();
                        cellValues = cellValues.toLowerCase();
                        if (cellValues.contains(serviceSearchText.textProperty().get().toLowerCase())) {
                            tableItems.add(data.get(a));
                            break;
                        }
                    }
                }
                serviceTable.setItems(tableItems);
            }
        });
    }

    @FXML
    private void addServiceAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/service/addService.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void serviceTableClicked() {
        enableControls();
        ServiceDTO selectedItem = serviceTable.getSelectionModel().getSelectedItem();
        serviceIdText.setText(selectedItem.getServiceId() + "");
        descriptionText.setText(selectedItem.getDescription());
        unitPriceText.setText(selectedItem.getServicePrice() + "");
        descriptionText.requestFocus();
    }

    private void enableControls() {
        descriptionText.setDisable(false);
        unitPriceText.setDisable(false);
        deleteButton.setDisable(false);
        updateButton.setDisable(false);
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
                    boolean isUpdated = serviceBO.updateService(new ServiceDTO(Integer.parseInt(serviceIdText.getText()), descriptionText.getText(), new BigDecimal(unitPriceText.getText())));
                    if (isUpdated) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Service Updated", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        loadServices();
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
        serviceIdText.setText(null);
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
                    boolean isDeleted = serviceBO.deleteService(Integer.parseInt(serviceIdText.getText()));
                    if (isDeleted) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Service Deleted", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        loadServices();
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
