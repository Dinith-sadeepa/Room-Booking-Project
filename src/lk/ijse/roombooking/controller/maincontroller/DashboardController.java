package lk.ijse.roombooking.controller.maincontroller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.ReservationBO;
import lk.ijse.roombooking.business.custom.ReservedBO;
import lk.ijse.roombooking.business.custom.RoomBO;
import lk.ijse.roombooking.model.ReservationDTO;
import lk.ijse.roombooking.model.ReservedDTO;
import lk.ijse.roombooking.model.RoomDTO;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private ReservedBO reservedBO;
    private RoomBO roomBO;
    private ReservationBO reservationBO;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private JFXButton reservedRoomsButton;

    @FXML
    private JFXButton availableRoomsButton;

    @FXML
    private JFXButton bookedRoomsButton;

    @FXML
    private JFXButton todayReservationButton;

    public DashboardController() {
        reservedBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVED);
        roomBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
        reservationBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
        loadCounts();
    }

    private void loadCounts() {
        int availableRoomsCount = 0;
        int bookedRoomsCount = 0;
        int todayReservationCount = 0;

        try {
            // room counts
            ArrayList<RoomDTO> allRoom = roomBO.getAllRoom();
            for (RoomDTO roomDTO : allRoom) {
                String status = roomDTO.getStatus();
                if (status.equals("Available")) {
                    availableRoomsCount++;
                } else if (status.equals("Booked")) {
                    bookedRoomsCount++;
                }
            }
            availableRoomsButton.setText(availableRoomsCount + "");
            bookedRoomsButton.setText(bookedRoomsCount + "");

            //reserved counts
            ArrayList<ReservedDTO> allReserveds = reservedBO.getAllReserveds();
            reservedRoomsButton.setText(allReserveds.size() + "");

            //today reservation counts
            ArrayList<ReservationDTO> allReservations = reservationBO.getAllReservations();
            for (ReservationDTO reservationDTO : allReservations) {
                Date checkInDate = reservationDTO.getCheckInDate();
                if (checkInDate.getDate() == new java.util.Date().getDate()) {
                    todayReservationCount++;
                }
            }
            todayReservationButton.setText(todayReservationCount + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), dashboardPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}
