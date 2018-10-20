package lk.ijse.roombooking.controller.bookingcontroller;

import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.*;
import lk.ijse.roombooking.common.validation.Validatation;
import lk.ijse.roombooking.model.*;
import org.controlsfx.control.textfield.TextFields;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DepartingController implements Initializable {

    @FXML
    private AnchorPane departingPane;

    @FXML
    private JFXTextField guestNicText;


    @FXML
    private JFXTextField departingIdText;

    @FXML
    private JFXTextField reservationIdText;

    @FXML
    private JFXDatePicker checkInDate;

    @FXML
    private JFXDatePicker checkOutDate;

    @FXML
    private JFXTimePicker checkInTime;

    @FXML
    private JFXTimePicker checkOutTime;

    @FXML
    private TableView<RoomDTO> bookingTable;

    @FXML
    private JFXTextField bankText;

    @FXML
    private JFXTextField branchText;

    @FXML
    private Label billTotalLabel;

    @FXML
    private JFXButton payButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXRadioButton chequeRButton;

    @FXML
    private ToggleGroup paymentMethod;

    @FXML
    private JFXTextField chequeNumberText;

    @FXML
    private JFXDatePicker dateOfExpire;

    @FXML
    private JFXRadioButton cashRButton;

    @FXML
    private Label amountInArrears;

    @FXML
    private Label paidLabel;

    @FXML
    private JFXTextField payingText;

    @FXML
    private Label balanceLabel;

    private ReservationBO reservationBO;
    private DepartingBO departingBO;
    private GuestBO guestBO;
    private RoomBO roomBO;
    private PaymentBO paymentBO;
    private ChequeDetailBO chequeDetailBO;

    public DepartingController() {
        departingBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.DEPARTING);
        reservationBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);
        guestBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.GUEST);
        roomBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
        paymentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
        chequeDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CHEQUEDETAIL);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
        reservationIdText.requestFocus();
        loadDepartingId();
        loadReservationIds();
        loadCheckOuts();
    }

    private void loadDepartingId() {
        try {
            ArrayList<Integer> departingIds = departingBO.getDepartingIds();
            if (departingIds.isEmpty()) {
                departingIdText.setText(1 + "");
            } else {
                for (Integer id : departingIds) {
                    departingIdText.setText((++id) + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCheckOuts() {
        checkOutDate.setValue(LocalDate.parse(new SimpleDateFormat("YYYY-MM-dd").format(new Date())));
        checkOutTime.setValue(LocalTime.parse(new SimpleDateFormat("hh:mm:ss").format(new Date())));
    }


    private void loadReservationIds() {
        try {

            ArrayList<Integer> reservationIds = reservationBO.getReservationId();
            TextFields.bindAutoCompletion(reservationIdText, reservationIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), departingPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    private void reservationIdTextAction(ActionEvent event) {
        String reservationId = reservationIdText.getText();
        ObservableList<RoomDTO> roomDTOS = FXCollections.observableArrayList();
        try {
            GuestDTO guestDTO = guestBO.getGuestNic(reservationId);
            guestNicText.setText(guestDTO.getNic());

            ReservationDTO reservationDTO = reservationBO.searchReservation(Integer.parseInt(reservationId));
            checkInDate.setValue(dateToLocalDate(reservationDTO.getCheckInDate()));
            checkInTime.setValue(timeToLocalTime(reservationDTO.getCheckInTime()));
            billTotalLabel.setText(reservationDTO.getReservationFee() + "");

            ArrayList<RoomDTO> roomDTOArrayList = roomBO.searchRoomForDeparting(Integer.parseInt(reservationId));

            for (RoomDTO roomDTO : roomDTOArrayList) {
                roomDTOS.add(roomDTO);
            }
            loadColumnsInBookingTable();
            bookingTable.setItems(roomDTOS);

            PaymentDTO paymentDTO = paymentBO.searchPaymentsByReservationId(Integer.parseInt(reservationId));
            if (paymentDTO == null) {
                alertMethod("Guest isn't yet pay reservation fee!", reservationIdText);
                reservationIdText.selectAll();
            } else {
                paidLabel.setText(paymentDTO.getAmount() + "");
                BigDecimal total = new BigDecimal(billTotalLabel.getText());
                amountInArrears.setText(total.subtract(paymentDTO.getAmount()) + "");
                payingText.requestFocus();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void payingTextAction(ActionEvent event) {
        if (checkPayingAmount()) {
            if (validatePayingAmount()) {
                BigDecimal arrears = new BigDecimal(amountInArrears.getText());
                BigDecimal paying = new BigDecimal(payingText.getText());
                balanceLabel.setText(paying.subtract(arrears) + "");
                if (paying.compareTo(arrears) >= 0) {
                    payButton.setDisable(false);
                } else {
                    alertMethod("You must pay arrears for depart!", payingText);
                    payingText.selectAll();
                }
            }
        }
    }

    private boolean validatePayingAmount() {
        if (Validatation.pricesValidate(payingText.getText())) {
            return true;
        } else {
            alertMethod("Paying Price is Invalid!", payingText);
            payingText.selectAll();
            return false;
        }
    }

    private boolean checkPayingAmount() {
        if (payingText.getText().trim().isEmpty()) {
            alertMethod("You must pay first!", payingText);
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private void payButtonAction(ActionEvent event) {
        try {
            int reservationId = Integer.parseInt(reservationIdText.getText());
            Date checkOutD = localDateToDate(checkOutDate.getValue());
            Time checkOutT = localTimeToTime(checkOutTime.getValue());
            BigDecimal paying = new BigDecimal(amountInArrears.getText());
            int departingId = Integer.parseInt(departingIdText.getText());

            int paymentId = 0;
            ArrayList<PaymentDTO> paymentDTOArrayList = paymentBO.getAllPayments();
            if (paymentDTOArrayList.isEmpty()) {
                paymentId = 1;
            } else {
                ArrayList<Integer> paymentIds = new ArrayList<>();
                for (PaymentDTO paymentDTO : paymentDTOArrayList) {
                    paymentIds.add(paymentDTO.getPaymentId());
                }
                for (Integer payId : paymentIds) {
                    paymentId = (++payId);
                }
            }

            String paymentMethod;
            if (cashRButton.isSelected()) {
                paymentMethod = "cash";
                ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
                paymentDTOS.add(new PaymentDTO(reservationId, 0, paymentMethod, paying, checkOutD));

                boolean isAdded = departingBO.addDeparting(new DepartingDTO(departingId, reservationId, checkOutD, checkOutT, paymentDTOS));
                if (isAdded) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Departing Complete", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/booking/departing.fxml"));
                    departingPane.getChildren().setAll(root);
                }
            } else {
                paymentMethod = "cheque";

                ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
                paymentDTOS.add(new PaymentDTO(reservationId, paymentId, paymentMethod, paying, checkOutD));

                int chequeDetailId = 0;
                ArrayList<ChequeDetailDTO> chequeDetails = chequeDetailBO.getAllChequeDetails();
                if (chequeDetails.isEmpty()) {
                    chequeDetailId = 1;
                } else {
                    ArrayList<Integer> chequeDetailIds = new ArrayList<>();
                    for (ChequeDetailDTO chequeDetailDTO : chequeDetails) {
                        chequeDetailIds.add(chequeDetailDTO.getChequeDetailId());
                    }
                    for (Integer cheDetailId : chequeDetailIds) {
                        cheDetailId = (++cheDetailId);
                    }
                }

                ArrayList<ChequeDetailDTO> chequeDetailDTOArrayList = new ArrayList<>();
                chequeDetailDTOArrayList.add(new ChequeDetailDTO(chequeDetailId, paymentId, localDateToDate(dateOfExpire.getValue()), chequeNumberText.getText(), bankText.getText(), branchText.getText()));
                boolean isAdded = departingBO.addDeparting(new DepartingDTO(departingId, reservationId, checkOutD, checkOutT, paymentDTOS, chequeDetailDTOArrayList));
                if (isAdded) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Departing Complete with Cheque", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/booking/departing.fxml"));
                    departingPane.getChildren().setAll(root);
                    reservationIdText.requestFocus();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadColumnsInBookingTable() {
        bookingTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        bookingTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomCategory"));
        bookingTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        bookingTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("bedType"));
        bookingTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("roomFloor"));
        bookingTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
    }

    @FXML
    private void chequeRAction(ActionEvent event) {
        chequeNumberText.setDisable(false);
        dateOfExpire.setDisable(false);
        bankText.setDisable(false);
        branchText.setDisable(false);
        chequeNumberText.requestFocus();
    }

    @FXML
    private void cashRAction(ActionEvent event) {
        chequeNumberText.setDisable(true);
        dateOfExpire.setDisable(true);
        bankText.setDisable(true);
        branchText.setDisable(true);
        payingText.requestFocus();
        payingText.selectAll();
    }

    @FXML
    private void chequeNoAction(ActionEvent event) {
        dateOfExpire.requestFocus();
    }

    @FXML
    private void dateOfExpireAction(ActionEvent event) {
        bankText.requestFocus();
    }

    @FXML
    private void bankAction(ActionEvent event) {
        branchText.requestFocus();
    }

    @FXML
    private void branchAction(ActionEvent event) {
        payingText.requestFocus();
    }


    private Date localDateToDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    private Time localTimeToTime(LocalTime time) {
        return java.sql.Time.valueOf(time);
    }

    private LocalDate dateToLocalDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    private LocalTime timeToLocalTime(Time time) {
        return new java.sql.Time(time.getTime()).toLocalTime();
    }

    private void alertMethod(String msg, Control controller) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
        controller.requestFocus();
    }
}
