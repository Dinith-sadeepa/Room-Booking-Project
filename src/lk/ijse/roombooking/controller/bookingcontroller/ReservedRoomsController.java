package lk.ijse.roombooking.controller.bookingcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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

public class ReservedRoomsController implements Initializable {

    @FXML
    private AnchorPane reservedPane;

    @FXML
    private JFXTextField nicNumberText;

    @FXML
    private JFXTextField guestIdText;

    @FXML
    private JFXTextField telephoneNoText;

    @FXML
    private JFXDatePicker checkInDate;

    @FXML
    private JFXDatePicker checkOutDate;

    @FXML
    private JFXDatePicker reservedDate;

    @FXML
    private JFXTextField guestNameText;

    @FXML
    private JFXTextField reservationIdText;

    @FXML
    private TableView<RoomDTO> roomTable;

    @FXML
    private JFXTextField bankText;

    @FXML
    private JFXTextField branchText;

    @FXML
    private JFXButton payButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXRadioButton chequeRButton;

    @FXML
    private ToggleGroup paymentMethod;

    @FXML
    private JFXTextField chequeNoText;

    @FXML
    private JFXDatePicker dateOfExpire;

    @FXML
    private JFXRadioButton cashRButton;

    @FXML
    private Label reservationFeeLabel;

    @FXML
    private JFXTextField paidText;

    @FXML
    private Label balanceLabel;

    @FXML
    private TableView<FacilityDTO> facilityTable;

    @FXML
    private TableView<ServiceDTO> serviceTable;

    private ReservedBO reservedBO;
    private ReservationBO reservationBO;
    private RoomBO roomBO;
    private GuestBO guestBO;
    private FacilityBO facilityBO;
    private ServiceBO serviceBO;
    private PaymentBO paymentBO;
    private ChequeDetailBO chequeDetailBO;

    public ReservedRoomsController() {
        reservedBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVED);
        reservationBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);
        roomBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
        guestBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.GUEST);
        facilityBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.FACILITY);
        serviceBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SERVICE);
        paymentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
        chequeDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CHEQUEDETAIL);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
        loadNics();
    }

    private void loadNics() {
        try {
            ArrayList<GuestDTO> allGuestForReserved = guestBO.getAllGuestForReserved();
            ArrayList<String> guestNics = new ArrayList<>();
            for (GuestDTO guestDTO : allGuestForReserved) {
                guestNics.add(guestDTO.getNic());
            }

            TextFields.bindAutoCompletion(nicNumberText, guestNics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), reservedPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    void nicNumberAction(ActionEvent event) {
        String nic = nicNumberText.getText();
        ObservableList<RoomDTO> roomDTOS = FXCollections.observableArrayList();
        ObservableList<FacilityDTO> facilityDTOS = FXCollections.observableArrayList();
        ObservableList<ServiceDTO> serviceDTOS = FXCollections.observableArrayList();
        try {
            // load guest Details
            GuestDTO guestDTO = guestBO.searchGuestByNic(nic);
            loadGuestDetails(guestDTO);

            // load ReservationDetails
            ReservationDTO reservationDTO = reservationBO.searchReservationForReserved(guestDTO.getGuestId());
            loadReservationDetails(reservationDTO);

            // load ReservedDetails
            ReservedDTO reservedDTO = reservedBO.searchReservedsByReservationId(reservationDTO.getReservationId());
            loadReservedDetails(reservedDTO);

            // load RoomDetails
            loadColumnsForRoomTable();
            ArrayList<RoomDTO> roomDTOArrayList = roomBO.searchRoomForDeparting(reservationDTO.getReservationId());

            for (RoomDTO roomDTO : roomDTOArrayList) {
                roomDTOS.add(roomDTO);
            }
            roomTable.setItems(roomDTOS);

            // load FacilityDetails
            loadColumnsForFacility();
            ArrayList<FacilityDTO> facilityDTOArrayList = facilityBO.searchFacilityForReserved(reservationDTO.getReservationId());

            for (FacilityDTO facilityDTO : facilityDTOArrayList) {
                facilityDTOS.add(facilityDTO);
            }
            facilityTable.setItems(facilityDTOS);

            // load ServiceDetails
            loadColumnsForService();
            ArrayList<ServiceDTO> serviceDTOArrayList = serviceBO.searchServiceForReserved(reservationDTO.getReservationId());

            for (ServiceDTO serviceDTO : serviceDTOArrayList) {
                serviceDTOS.add(serviceDTO);
            }
            serviceTable.setItems(serviceDTOS);

            paidText.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadReservedDetails(ReservedDTO reservedDTO) {
        reservedDate.setValue(dateToLocalDate(reservedDTO.getReservedDate()));
    }

    private void loadReservationDetails(ReservationDTO reservationDTO) {
        reservationIdText.setText(reservationDTO.getReservationId() + "");
        checkInDate.setValue(dateToLocalDate(reservationDTO.getCheckInDate()));
        checkOutDate.setValue(dateToLocalDate(reservationDTO.getCheckOutDate()));
        reservationFeeLabel.setText(reservationDTO.getReservationFee() + "");
    }

    private void loadGuestDetails(GuestDTO guestDTO) {
        guestIdText.setText(guestDTO.getGuestId() + "");
        guestNameText.setText(guestDTO.getGender() + " " + guestDTO.getGuestName());
        telephoneNoText.setText(guestDTO.getTelephoneNo());
    }

    private void loadColumnsForRoomTable() {
        roomTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomCategory"));
        roomTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("bedType"));
        roomTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("roomFloor"));
        roomTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
    }

    private void loadColumnsForFacility() {
        facilityTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("facilityId"));
        facilityTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        facilityTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("facilityPrice"));
    }

    private void loadColumnsForService() {
        serviceTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        serviceTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        serviceTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
    }

    @FXML
    void paidTextAction(ActionEvent event) {
        if (paidText.getText().trim().isEmpty()) {
            alertMethod("Please Pay to Reserved!", paidText);
        } else {
            if (validatePaidText()) {
                BigDecimal paidCost = new BigDecimal(paidText.getText());
                BigDecimal totalCost = new BigDecimal(reservationFeeLabel.getText());
                if (paidCost.compareTo(totalCost.divide(new BigDecimal(2))) >= 0) {
                    payButton.setDisable(false);
                    balanceLabel.setText(paidCost.subtract(totalCost) + "");
                } else {
                    alertMethod("You must paid at least half of reserved fee!", paidText);
                    paidText.selectAll();
                }
            }
        }
    }

    private boolean validatePaidText() {
        if (Validatation.pricesValidate(paidText.getText())) {
            return true;
        } else {
            alertMethod("paid price is Invalid!", paidText);
            paidText.selectAll();
            return false;
        }
    }

    @FXML
    void payButtonAction(ActionEvent event) {
        try {
            // getData
            int reservationId = Integer.parseInt(reservationIdText.getText());
            BigDecimal paying = new BigDecimal(paidText.getText());
            LocalDate date = LocalDate.parse(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
            Integer paymentId = 0;
            ArrayList<Integer> paymentIds = paymentBO.getPaymentIds();
            if (paymentIds.isEmpty()) {
                paymentId = 1;
            } else {
                for (Integer payId : paymentIds) {
                    paymentId = (++payId);
                }
            }
            String paymentMethod;
            if (cashRButton.isSelected()) {
                paymentMethod = "cash";
                // added to payments
                boolean isAdded = paymentBO.addPayment(new PaymentDTO(reservationId, paymentId, paymentMethod, paying, localDateToDate(date)));
                if (isAdded) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Payment is Done!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/booking/reservedRooms.fxml"));
                    reservedPane.getChildren().setAll(root);
                }
            } else {
                paymentMethod = "cheque";
                // added to payments
                boolean isAdded = paymentBO.addPayment(new PaymentDTO(reservationId, paymentId, paymentMethod, paying, localDateToDate(date)));
                if (isAdded) {
                    // get cheque detailId
                    int chequeDetailId = 0;
                    ArrayList<ChequeDetailDTO> allChequeDetails = chequeDetailBO.getAllChequeDetails();
                    if (allChequeDetails.isEmpty()) {
                        chequeDetailId = 1;
                    } else {
                        ArrayList<Integer> chequeDetailIds = new ArrayList<>();
                        for (ChequeDetailDTO chequeDetailDTO : allChequeDetails) {
                            chequeDetailIds.add(chequeDetailDTO.getChequeDetailId());
                        }
                        for (Integer cheDetailId : chequeDetailIds) {
                            cheDetailId = (++cheDetailId);
                        }
                    }

                    // add to cheque details
                    boolean isAddedChequeDetail = chequeDetailBO.addChequeDetail(new ChequeDetailDTO(chequeDetailId, paymentId, localDateToDate(dateOfExpire.getValue()), chequeNoText.getText(), bankText.getText(), branchText.getText()));
                    if (isAddedChequeDetail) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Payment is Done With Cheque!", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        AnchorPane root = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/booking/reservedRooms.fxml"));
                        reservedPane.getChildren().setAll(root);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void chequeRAction(ActionEvent event) {
        chequeNoText.setDisable(false);
        dateOfExpire.setDisable(false);
        bankText.setDisable(false);
        branchText.setDisable(false);
        chequeNoText.requestFocus();
    }

    @FXML
    private void cashRAction(ActionEvent event) {
        chequeNoText.setDisable(true);
        dateOfExpire.setDisable(true);
        bankText.setDisable(true);
        branchText.setDisable(true);
        paidText.requestFocus();
        paidText.selectAll();
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
        paidText.requestFocus();
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
