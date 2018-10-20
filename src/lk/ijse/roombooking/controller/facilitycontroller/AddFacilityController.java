package lk.ijse.roombooking.controller.facilitycontroller;

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
import lk.ijse.roombooking.business.custom.FacilityBO;
import lk.ijse.roombooking.common.validation.Validatation;
import lk.ijse.roombooking.model.FacilityDTO;
import org.controlsfx.control.textfield.TextFields;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddFacilityController implements Initializable {

    @FXML
    private JFXTextField facilityIdText;

    @FXML
    private JFXTextField facilityDescriptionText;

    @FXML
    private JFXTextField unitPriceText;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private ImageView closeImage;

    private FacilityBO facilityBO;

    public AddFacilityController() {
        facilityBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.FACILITY);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genarateFacilityId();
        loadOptions();
    }

    private void loadOptions() {
        try {
            ArrayList<FacilityDTO> facilityDTOArrayList = facilityBO.getAllFacilities();

            ArrayList<String> descriptions = new ArrayList<>();
            ArrayList<BigDecimal> facilityPrices = new ArrayList<>();

            for (FacilityDTO facilityDTO : facilityDTOArrayList) {
                descriptions.add(facilityDTO.getDescription());
                facilityPrices.add(facilityDTO.getFacilityPrice());
            }

            TextFields.bindAutoCompletion(facilityDescriptionText, descriptions);
            TextFields.bindAutoCompletion(unitPriceText, facilityPrices);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genarateFacilityId() {
        try {
            ArrayList<FacilityDTO> facilityDTOS = facilityBO.getAllFacilities();
            if (facilityDTOS.isEmpty()) {
                facilityIdText.setText(1 + "");
            } else {
                ArrayList<Integer> facilityIds = new ArrayList<>();
                for (FacilityDTO facilityDTO : facilityDTOS) {
                    facilityIds.add(facilityDTO.getFacilityId());
                }
                for (Integer facilityId : facilityIds) {
                    facilityIdText.setText((++facilityId) + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeImageClicked() {
        Stage stage = (Stage) closeImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        try {
            String description = facilityDescriptionText.getText();
            String unitPrice = facilityDescriptionText.getText();

            if (description.trim().isEmpty()) {
                alertMethod(facilityDescriptionText, "Description can not be empty!");
                return;
            }
            if (unitPrice.trim().isEmpty()) {
                alertMethod(unitPriceText, "Unit price can not be empty!");
                return;
            }
            if (validateFacilityFields()) {
                boolean isAdded = facilityBO.addFacility(new FacilityDTO(Integer.parseInt(facilityIdText.getText()), facilityDescriptionText.getText(), new BigDecimal(unitPriceText.getText())));
                if (isAdded) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Facility Added", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    emptyFields();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateFacilityFields() {
        if (Validatation.stringsValidate(facilityDescriptionText.getText())) {
            if (Validatation.pricesValidate(unitPriceText.getText())) {
                return true;
            } else {
                alertMethod(unitPriceText, "Price is Invalid!");
                return false;
            }
        } else {
            alertMethod(facilityDescriptionText, "Description is Invalid!");
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
        facilityIdText.setText(null);
        facilityDescriptionText.setText(null);
        unitPriceText.setText(null);
        genarateFacilityId();
        facilityDescriptionText.requestFocus();
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        emptyFields();
    }

    @FXML
    void facilityDescriptionAction(ActionEvent event) {
        unitPriceText.requestFocus();
    }

    @FXML
    void facilityIdAction(ActionEvent event) {
        facilityDescriptionText.requestFocus();
    }

    @FXML
    void unitPriceAction(ActionEvent event) {
        addButtonAction(event);
    }

}
