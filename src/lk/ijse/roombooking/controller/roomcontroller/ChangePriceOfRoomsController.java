package lk.ijse.roombooking.controller.roomcontroller;

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
import lk.ijse.roombooking.business.custom.RoomBO;
import lk.ijse.roombooking.model.RoomDTO;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChangePriceOfRoomsController implements Initializable {

    @FXML
    private TableView<RoomDTO> roomTable;

    @FXML
    private JFXTextField roomIdText;

    @FXML
    private JFXTextField roomPriceText;

    @FXML
    private JFXTextField searchText;

    @FXML
    private JFXButton changeButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private ImageView closeImage;

    private RoomBO roomBO;

    private ObservableList<RoomDTO> data;

    public ChangePriceOfRoomsController() {
        roomBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadColumns();
        loadRooms();
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

    @FXML
    private void roomTableClicked() {
        RoomDTO selectedItem = roomTable.getSelectionModel().getSelectedItem();
        enableControls();
        roomIdText.setText(selectedItem.getRoomId() + "");
        roomPriceText.setText(selectedItem.getRoomPrice() + "");
        roomPriceText.requestFocus();
    }

    private void enableControls() {
        roomPriceText.setDisable(false);
        changeButton.setDisable(false);
        cancelButton.setDisable(false);
    }


    @FXML
    private void changeButtonAction(ActionEvent event) {
        RoomDTO selectedItem = roomTable.getSelectionModel().getSelectedItem();
        try {
            boolean isChanged = roomBO.updateRoom(new RoomDTO(Integer.parseInt(roomIdText.getText()), selectedItem.getBedType(), selectedItem.getRoomType(), selectedItem.getRoomCategory(), selectedItem.getRoomFloor(), new BigDecimal(roomPriceText.getText()), selectedItem.getStatus()));
            if (isChanged) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Room Price Changed", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                loadRooms();
                disableControls();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        disableControls();
    }

    private void disableControls() {
        roomIdText.setText(null);
        roomPriceText.setText(null);

        roomPriceText.setDisable(true);
        changeButton.setDisable(true);
        cancelButton.setDisable(true);
    }

    @FXML
    private void closeImageClicked() {
        Stage stage = (Stage) closeImage.getScene().getWindow();
        stage.close();
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
}
