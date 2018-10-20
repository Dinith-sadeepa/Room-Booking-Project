package lk.ijse.roombooking.controller.roomcontroller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.RoomBO;
import lk.ijse.roombooking.model.RoomDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomSearchController implements Initializable {

    @FXML
    private TableView<RoomDTO> roomTable;

    @FXML
    private AnchorPane roomSearchPane;

    @FXML
    private JFXTextField roomSearchText;

    private ObservableList<RoomDTO> data;

    private RoomBO roomBO;

    public RoomSearchController() {
        roomBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
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

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), roomSearchPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    private void roomSearchKeyReleased() {
        roomSearchText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (roomSearchText.textProperty().get().isEmpty()) {
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
                        if (cellValue.contains(roomSearchText.textProperty().get().toLowerCase())) {
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
