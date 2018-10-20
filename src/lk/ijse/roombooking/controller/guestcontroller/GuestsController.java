package lk.ijse.roombooking.controller.guestcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.GuestBO;
import lk.ijse.roombooking.business.custom.SignUpBO;
import lk.ijse.roombooking.model.GuestDTO;
import lk.ijse.roombooking.model.SignUpDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GuestsController implements Initializable {

    @FXML
    private TableView<GuestDTO> guestTable;

    @FXML
    private JFXTextField searchGuestText;

    @FXML
    private AnchorPane guestsPane;

    @FXML
    private JFXTextField guestIdText;

    @FXML
    private JFXTextField genderText;

    @FXML
    private JFXTextField guestNameText;

    @FXML
    private JFXTextField telephoneText;

    @FXML
    private JFXTextField nicText;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton deleteButton;

    private GuestBO guestBO;

    private ObservableList<GuestDTO> data;

    private SignUpBO signUpBO;

    public GuestsController() {
        guestBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.GUEST);
        signUpBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SIGNUP);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
        loadColumns();
        loadGuests();
    }

    private void loadColumns() {
        guestTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("guestId"));
        guestTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("gender"));
        guestTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("guestName"));
        guestTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nic"));
        guestTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("telephoneNo"));
    }

    private void loadGuests() {
        try {
            ArrayList<GuestDTO> guestDTOS = guestBO.getAllGuests();
            data = FXCollections.observableArrayList(guestDTOS);
            guestTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), guestsPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    private void searchGuestKeyReleased() {
        searchGuestText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchGuestText.textProperty().get().isEmpty()) {
                    guestTable.setItems(data);
                    return;
                }
                ObservableList<GuestDTO> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<GuestDTO, ?>> cols = guestTable.getColumns();
                for (int a = 0; a < data.size(); a++) {
                    for (int b = 0; b < cols.size(); b++) {
                        TableColumn cola = cols.get(b);
                        String cellValues = cola.getCellData(data.get(a)).toString();
                        cellValues = cellValues.toLowerCase();
                        if (cellValues.contains(searchGuestText.textProperty().get().toLowerCase())) {
                            tableItems.add(data.get(a));
                            break;
                        }
                    }
                }
                guestTable.setItems(tableItems);
            }
        });
    }

    @FXML
    private void guestTableClicked() {
        enableControls();
        GuestDTO selectedItem = guestTable.getSelectionModel().getSelectedItem();
        guestIdText.setText(selectedItem.getGuestId() + "");
        genderText.setText(selectedItem.getGender());
        guestNameText.setText(selectedItem.getGuestName());
        nicText.setText(selectedItem.getNic());
        telephoneText.setText(selectedItem.getTelephoneNo());

        genderText.requestFocus();
    }

    private void enableControls() {
        genderText.setDisable(false);
        guestNameText.setDisable(false);
        nicText.setDisable(false);
        telephoneText.setDisable(false);
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
                    boolean isUpdated = guestBO.updateGuest(new GuestDTO(Integer.parseInt(guestIdText.getText()), genderText.getText(), guestNameText.getText(), nicText.getText(), telephoneText.getText()));
                    if (isUpdated) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Guest Updated", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        loadGuests();
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

        guestIdText.setText(null);
        genderText.setText(null);
        guestNameText.setText(null);
        nicText.setText(null);
        telephoneText.setText(null);

        genderText.setDisable(true);
        guestNameText.setDisable(true);
        nicText.setDisable(true);
        telephoneText.setDisable(true);
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    @FXML
    private void deletebuttonAction(ActionEvent event) {
        try {
            SignUpDTO admin = signUpBO.getAdmin();

            TextInputDialog d = new TextInputDialog();
            d.setTitle("Input Administrator Password");
            d.setHeaderText(null);
            d.setContentText("Password :");
            Optional<String> result = d.showAndWait();

            if (result.isPresent()) {
                if (result.get().equals(admin.getPassword())) {
                    boolean isDeleted = guestBO.deleteGuest(Integer.parseInt(guestIdText.getText()));
                    if (isDeleted) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Guest Deleted", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        loadGuests();
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
    void genderAction(ActionEvent event) {
        guestNameText.requestFocus();
    }

    @FXML
    void guestNameAction(ActionEvent event) {
        nicText.requestFocus();
    }

    @FXML
    void nicAction(ActionEvent event) {
        telephoneText.requestFocus();
    }

    @FXML
    void telephoneAction(ActionEvent event) {
        updateButtonAction(event);
    }
}
