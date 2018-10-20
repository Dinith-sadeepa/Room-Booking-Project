package lk.ijse.roombooking.controller.roomcontroller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.RoomBO;
import lk.ijse.roombooking.common.validation.Validatation;
import lk.ijse.roombooking.model.RoomDTO;
import org.controlsfx.control.textfield.TextFields;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRoomController implements Initializable {

    private RoomBO roomBO;
    @FXML
    private ImageView closeImage;
    @FXML
    private JFXTextField roomIdText;
    @FXML
    private JFXTextField roomCategoryText;
    @FXML
    private JFXTextField roomTypeText;
    @FXML
    private JFXTextField bedTypeText;
    @FXML
    private JFXTextField roomFloorText;
    @FXML
    private JFXTextField roomPriceText;

    public AddRoomController() {
        roomBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomCategoryText.requestFocus();
        genarateRoomId();
        loadOptions();
    }

    private void loadOptions() {
        try {
            ArrayList<RoomDTO> roomDTOArrayList = roomBO.getAllRoom();

            ArrayList<String> bedTypes = new ArrayList<>();
            ArrayList<String> roomCategories = new ArrayList<>();
            ArrayList<String> roomTypes = new ArrayList<>();
            ArrayList<Integer> roomFloors = new ArrayList<>();
            ArrayList<BigDecimal> roomPrices = new ArrayList<>();

            for (RoomDTO roomDTO : roomDTOArrayList) {
                bedTypes.add(roomDTO.getBedType());
                roomCategories.add(roomDTO.getRoomCategory());
                roomTypes.add(roomDTO.getRoomType());
                roomFloors.add(roomDTO.getRoomFloor());
                roomPrices.add(roomDTO.getRoomPrice());
            }

            TextFields.bindAutoCompletion(bedTypeText, bedTypes);
            TextFields.bindAutoCompletion(roomTypeText, roomTypes);
            TextFields.bindAutoCompletion(roomCategoryText, roomCategories);
            TextFields.bindAutoCompletion(roomFloorText, roomFloors);
            TextFields.bindAutoCompletion(roomPriceText, roomPrices);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genarateRoomId() {
        try {
            ArrayList<Integer> roomIds = roomBO.getRoomIds();
            if (roomIds.isEmpty()) {
                roomIdText.setText(1 + "");
            } else {
                for (Integer roomId : roomIds) {
                    roomIdText.setText((++roomId) + "");
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
            String roomCategory = roomCategoryText.getText();
            String roomType = roomTypeText.getText();
            String bedType = bedTypeText.getText();
            String roomFloor = roomFloorText.getText();
            String roomPrice = roomPriceText.getText();

            if (roomCategory.trim().isEmpty()) {
                alertMethod("Room Category can not be empty!", roomCategoryText);
                return;
            }
            if (roomType.trim().isEmpty()) {
                alertMethod("Room Type can not be empty!", roomTypeText);
                return;
            }
            if (bedType.trim().isEmpty()) {
                alertMethod("Bed Type can not be empty!", bedTypeText);
                return;
            }
            if (roomFloor.trim().isEmpty()) {
                alertMethod("Room Floor can not be empty!", roomFloorText);
                return;
            }
            if (roomPrice.trim().isEmpty()) {
                alertMethod("Room price can not be empty!", roomPriceText);
                return;
            }

            if (validateRoomFields()) {
                boolean isAdded = roomBO.addRoom(new RoomDTO(Integer.parseInt(roomIdText.getText()), bedTypeText.getText(), roomTypeText.getText(), roomCategoryText.getText(), Integer.parseInt(roomFloorText.getText()), new BigDecimal((roomPriceText.getText())), "available"));
                if (isAdded) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Room Added", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    emptyFields();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateRoomFields() {
        if (Validatation.stringsValidate(roomCategoryText.getText())) {
            if (Validatation.stringsValidate(roomTypeText.getText())) {
                if (Validatation.stringsValidate(bedTypeText.getText())) {
                    if (Validatation.integerValueValidate(roomFloorText.getText())) {
                        if (Validatation.pricesValidate(roomPriceText.getText())) {
                            return true;
                        } else {
                            alertMethod("Room Price Is Invalid!", roomPriceText);
                            return false;
                        }
                    } else {
                        alertMethod("Room Floor must be a numeric!", roomPriceText);
                        return false;
                    }
                } else {
                    alertMethod("Bed Type Is Invalid!", roomPriceText);
                    return false;
                }
            } else {
                alertMethod("Room type Is Invalid!", roomPriceText);
                return false;
            }
        } else {
            alertMethod("Room Category Is Invalid!", roomPriceText);
            return false;
        }
    }

    private void alertMethod(String msg, JFXTextField textField) {
        Alert error = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        error.show();
        textField.requestFocus();
        textField.selectAll();
    }


    private void emptyFields() {
        roomIdText.setText(null);
        roomCategoryText.setText(null);
        roomFloorText.setText(null);
        roomPriceText.setText(null);
        roomTypeText.setText(null);
        bedTypeText.setText(null);
        genarateRoomId();
        roomCategoryText.requestFocus();
    }

    @FXML
    void roomIdAction(ActionEvent event) {
        roomCategoryText.requestFocus();
    }

    @FXML
    void roomCategoryAction(ActionEvent event) {
        roomTypeText.requestFocus();
    }

    @FXML
    void roomFloorAction(ActionEvent event) {
        roomPriceText.requestFocus();
    }

    @FXML
    void roomPriceAction(ActionEvent event) {
        addButtonAction(event);
    }

    @FXML
    void roomTypeAction(ActionEvent event) {
        bedTypeText.requestFocus();
    }

    @FXML
    void bedTypeAction(ActionEvent event) {
        roomFloorText.requestFocus();
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        emptyFields();
    }
}
