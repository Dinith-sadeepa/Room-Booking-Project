package lk.ijse.roombooking.controller.roomcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.RoomBO;
import lk.ijse.roombooking.business.custom.SignUpBO;
import lk.ijse.roombooking.model.RoomDTO;
import lk.ijse.roombooking.model.SignUpDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class RoomMasterController implements Initializable {

    @FXML
    private AnchorPane roomMasterPane;

    @FXML
    private TableView<RoomDTO> roomTable;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXTextField roomIdText;

    @FXML
    private JFXTextField roomCategoryText;

    @FXML
    private JFXTextField roomTypeText;

    @FXML
    private JFXTextField roomPriceText;

    @FXML
    private JFXTextField bedTypeText;

    @FXML
    private JFXTextField roomFloorText;

    @FXML
    private JFXTextField statusText;

    @FXML
    private JFXTextField searchText;

    private RoomBO roomBO;

    private ObservableList<RoomDTO> data;

    private SignUpBO signUpBO;

    public RoomMasterController() {
        roomBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
        signUpBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SIGNUP);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
        loadRooms();
        loadColumns();
    }

    private void loadColumns() {
        roomTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomCategory"));
        roomTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomFloor"));
        roomTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("bedType"));
        roomTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
        roomTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadRooms() {
        try {
            ArrayList<RoomDTO> roomList = roomBO.getAllRoom();
            data = FXCollections.observableArrayList(roomList);
            roomTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), roomMasterPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    private void addRoomAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/roombooking/view/room/addRoom.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void printButtonAction(ActionEvent event) {

    }

    @FXML
    private void roomTableClicked() {
        enableControls();

        RoomDTO selectedItem = roomTable.getSelectionModel().getSelectedItem();
        roomIdText.setText(selectedItem.getRoomId() + "");
        roomCategoryText.setText(selectedItem.getRoomCategory());
        roomTypeText.setText(selectedItem.getRoomType());
        roomPriceText.setText(selectedItem.getRoomPrice() + "");
        bedTypeText.setText(selectedItem.getBedType());
        roomFloorText.setText(selectedItem.getRoomFloor() + "");
        statusText.setText(selectedItem.getStatus());
        roomCategoryText.requestFocus();
    }

    private void enableControls() {
        roomCategoryText.setDisable(false);
        roomFloorText.setDisable(false);
        roomTypeText.setDisable(false);
        bedTypeText.setDisable(false);
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
                    boolean isUpdated = roomBO.updateRoom(new RoomDTO(Integer.parseInt(roomIdText.getText()), bedTypeText.getText(), roomTypeText.getText(), roomCategoryText.getText(), Integer.parseInt(roomFloorText.getText()), new BigDecimal(roomPriceText.getText()), statusText.getText()));
                    if (isUpdated) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Room Updated", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        loadRooms();
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
        roomIdText.setText(null);
        roomCategoryText.setText(null);
        roomFloorText.setText(null);
        roomPriceText.setText(null);
        roomTypeText.setText(null);
        bedTypeText.setText(null);
        statusText.setText(null);

        roomCategoryText.setDisable(true);
        roomFloorText.setDisable(true);
        roomTypeText.setDisable(true);
        bedTypeText.setDisable(true);
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    @FXML
    private void deleteButtonAction() {
        try {
            SignUpDTO admin = signUpBO.getAdmin();

            TextInputDialog d = new TextInputDialog();
            d.setTitle("Input Administrator Password");
            d.setHeaderText(null);
            d.setContentText("Password :");
            Optional<String> result = d.showAndWait();

            if (result.isPresent()) {
                if (result.get().equals(admin.getPassword())) {
                    boolean isDeleted = roomBO.deleteRoom(Integer.parseInt(roomIdText.getText()));
                    if (isDeleted) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Room Deleted", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        loadRooms();
                        disableControls();
                    }
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Administrator Password Incorrect!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void roomSearchKeyReleased() {
        searchText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchText.textProperty().get().isEmpty()) {
                    roomTable.setItems(data);
                    return;
                }
                ObservableList<RoomDTO> tableItem = FXCollections.observableArrayList();
                ObservableList<TableColumn<RoomDTO, ?>> cols = roomTable.getColumns();

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
                roomTable.setItems(tableItem);
            }
        });
    }

    @FXML
    void roomCategoryAction(ActionEvent event) {
        roomTypeText.requestFocus();
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
    void roomFloorAction(ActionEvent event) {
        updateButtonAction(event);
    }

}
