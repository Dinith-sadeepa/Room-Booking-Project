package lk.ijse.roombooking.controller.servicecontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.ServiceBO;
import lk.ijse.roombooking.common.validation.Validatation;
import lk.ijse.roombooking.model.ServiceDTO;
import org.controlsfx.control.textfield.TextFields;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddServiceController implements Initializable {

    @FXML
    private JFXTextField serviceIdText;

    @FXML
    private JFXTextField serviceDescriptionText;

    @FXML
    private JFXTextField serviceUnitPriceText;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private ImageView closeImage;


    private ServiceBO serviceBO;

    public AddServiceController() {
        serviceBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SERVICE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genarateServiceIds();
        loadOptions();
    }

    private void loadOptions() {
        try {
            ArrayList<ServiceDTO> serviceDTOArrayList = serviceBO.getAllService();

            ArrayList<String> descriptions = new ArrayList<>();
            ArrayList<BigDecimal> servicePrices = new ArrayList<>();

            for (ServiceDTO serviceDTO : serviceDTOArrayList) {
                descriptions.add(serviceDTO.getDescription());
                servicePrices.add(serviceDTO.getServicePrice());
            }

            TextFields.bindAutoCompletion(serviceDescriptionText, descriptions);
            TextFields.bindAutoCompletion(serviceUnitPriceText, servicePrices);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genarateServiceIds() {
        try {
            ArrayList<ServiceDTO> serviceDTOArrayList = serviceBO.getAllService();
            if (serviceDTOArrayList.isEmpty()) {
                serviceIdText.setText(1 + "");
            } else {
                ArrayList<Integer> serviceIds = new ArrayList<>();
                for (ServiceDTO serviceDTO : serviceDTOArrayList) {
                    serviceIds.add(serviceDTO.getServiceId());
                }
                for (Integer serviceId : serviceIds) {
                    serviceIdText.setText((++serviceId) + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        try {
            String description = serviceDescriptionText.getText();
            String unitPrice = serviceUnitPriceText.getText();

            if (description.trim().isEmpty()) {
                alertMethod(serviceDescriptionText, "Description can not be empty!");
                return;
            }
            if (unitPrice.trim().isEmpty()) {
                alertMethod(serviceUnitPriceText, "Unit price can not be empty!");
                return;
            }
            if (validateServiceFields()) {
                boolean isAdded = serviceBO.addService(new ServiceDTO(Integer.parseInt(serviceIdText.getText()), serviceDescriptionText.getText(), new BigDecimal(serviceUnitPriceText.getText())));
                if (isAdded) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Service Added", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    emptyFields();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateServiceFields() {
        if (Validatation.stringsValidate(serviceDescriptionText.getText())) {
            if (Validatation.pricesValidate(serviceUnitPriceText.getText())) {
                return true;
            } else {
                alertMethod(serviceUnitPriceText, "unit Price is Invalid!");
                return false;
            }
        } else {
            alertMethod(serviceDescriptionText, "Description is Invalid!");
            return false;
        }
    }

    private void alertMethod(JFXTextField textField, String msg) {
        Alert error = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        error.show();
        textField.requestFocus();
        textField.selectAll();
    }

    private void emptyFields() {
        serviceIdText.setText(null);
        serviceDescriptionText.setText(null);
        serviceUnitPriceText.setText(null);
        genarateServiceIds();
        serviceDescriptionText.requestFocus();
    }

    @FXML
    private void closeImageClicked() {
        Stage stage = (Stage) closeImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        emptyFields();
    }

    @FXML
    void descriptionAction(ActionEvent event) {
        serviceUnitPriceText.requestFocus();
    }

    @FXML
    void serviceIdAction(ActionEvent event) {
        serviceDescriptionText.requestFocus();
    }

    @FXML
    void serviceUnitPriceAction(ActionEvent event) {
        addButtonAction(event);
    }
}
