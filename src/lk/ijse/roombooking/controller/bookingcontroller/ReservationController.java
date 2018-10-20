package lk.ijse.roombooking.controller.bookingcontroller;

import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import lk.ijse.roombooking.business.BOFactory;
import lk.ijse.roombooking.business.custom.*;
import lk.ijse.roombooking.common.validation.Validatation;
import lk.ijse.roombooking.model.*;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    ObservableList<FacilityDTO> facilityDTOS;
    ObservableList<RoomDTO> data = FXCollections.observableArrayList();
    @FXML
    private AnchorPane reservationPane;
    @FXML
    private JFXDatePicker checkInDate;
    @FXML
    private JFXDatePicker checkOutDate;
    @FXML
    private JFXTextField noOfAdultsText;
    @FXML
    private Label noOfNightsLabel;
    @FXML
    private JFXComboBox<String> roomCategoryCombo;
    @FXML
    private JFXComboBox<String> roomTypeCombo;
    @FXML
    private JFXComboBox<String> bedTypeCombo;
    @FXML
    private JFXTextField roomQtyText;
    @FXML
    private JFXButton searchButton;
    @FXML
    private TableView<RoomDTO> bookingTable;
    @FXML
    private TableColumn<RoomDTO, Integer> roomIdColumn;
    @FXML
    private TableColumn<RoomDTO, BigDecimal> roomPriceColumn;
    @FXML
    private JFXTextField roomPriceText;
    @FXML
    private JFXButton removeButton;
    @FXML
    private JFXTextField reservationIdText;
    @FXML
    private JFXTimePicker checkInTime;
    @FXML
    private JFXTextField nicText;
    @FXML
    private JFXTextField guestIdText;
    @FXML
    private JFXComboBox<String> genderCombo;
    @FXML
    private JFXTextField guestNameText;
    @FXML
    private JFXTextField telephoneText;
    @FXML
    private JFXTextField bankText;
    @FXML
    private JFXTextField branchText;
    @FXML
    private JFXTextField roomsCostText;
    @FXML
    private JFXTextField extrasText;
    @FXML
    private JFXTextField paymentIdText;
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
    private JFXDatePicker dateOfExpireDate;
    @FXML
    private JFXRadioButton cashRButton;
    @FXML
    private JFXTextField paidText;
    @FXML
    private Label balanceLabel;
    @FXML
    private JFXRadioButton reservingRButton;
    @FXML
    private ToggleGroup payment;
    @FXML
    private JFXRadioButton onPaymentRButton;
    @FXML
    private TableView<FacilityDTO> facilityTable;
    @FXML
    private TableColumn<?, ?> selectFacilityColumn;
    @FXML
    private TableView<ServiceDTO> serviceTable;
    @FXML
    private TableColumn<?, ?> selectFacilityColumn1;
    @FXML
    private JFXComboBox<String> selectFacilitiesCombo;
    @FXML
    private JFXComboBox<String> selectServicesCombo;
    @FXML
    private AnchorPane selectRoomsPane;
    @FXML
    private GridPane roomButtonGridPane;
    private ReservationBO reservationBO;
    private ReservedBO reservedBO;
    private ServiceBO serviceBO;
    private FacilityBO facilityBO;
    private RoomBO roomBO;
    private GuestBO guestBO;
    private PaymentBO paymentBO;

    public ReservationController() {
        reservationBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);
        reservedBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVED);
        facilityBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.FACILITY);
        roomBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
        serviceBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SERVICE);
        guestBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.GUEST);
        paymentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transition();
        checkOutDate.requestFocus();
        setCheckInDate();
        setCheckInTime();
        serviceComboLoad();
        facilityComboLoad();
        roomCategoryComboLoad();
        roomTypeComboLoad();
        bedTypeComboLoad();
        loadGuestNICs();
        reservationIdLoad();
        paymentIdLoad();
    }

    private void loadGuestNICs() {
        try {
            ArrayList<String> nics = guestBO.getAllNics();
            TextFields.bindAutoCompletion(nicText, nics);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void paymentIdLoad() {
        try {
            ArrayList<Integer> paymentIds = paymentBO.getPaymentIds();
            if (paymentIds.isEmpty()) {
                paymentIdText.setText(1 + "");
            } else {
                for (Integer paymentId : paymentIds) {
                    paymentIdText.setText((++paymentId) + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reservationIdLoad() {
        try {
            ArrayList<ReservationDTO> reservationDTOArrayList = reservationBO.getAllReservations();
            if (reservationDTOArrayList.isEmpty()) {
                reservationIdText.setText(1 + "");
            } else {
                ArrayList<Integer> reservationIds = new ArrayList<>();
                for (ReservationDTO reservationDTO : reservationDTOArrayList) {
                    reservationIds.add(reservationDTO.getReservationId());
                }
                for (Integer reservationId : reservationIds) {
                    reservationIdText.setText((++reservationId) + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bedTypeComboLoad() {
        try {
            ArrayList<String> type = roomBO.getBedTypes();
            ObservableList<String> bedTypeList = FXCollections.observableArrayList();
            for (String bedType : type) {
                bedTypeList.add(bedType);
            }
            bedTypeCombo.setItems(bedTypeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void roomTypeComboLoad() {
        try {
            ArrayList<String> type = roomBO.getRoomTypes();
            ObservableList<String> typeList = FXCollections.observableArrayList();
            for (String cat : type) {
                typeList.add(cat);
            }
            roomTypeCombo.setItems(typeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void roomCategoryComboLoad() {
        try {
            ArrayList<String> category = roomBO.getRoomCategories();
            ObservableList<String> categoryList = FXCollections.observableArrayList();
            for (String cat : category) {
                categoryList.add(cat);
            }
            roomCategoryCombo.setItems(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCheckInTime() {
        checkInTime.setValue(LocalTime.parse(new SimpleDateFormat("hh:mm:ss").format(new Date())));
    }

    private void serviceComboLoad() {
        try {
            ArrayList<String> serviceList = serviceBO.getAllServicesDescriptions();
            ObservableList<String> serviceObservable = FXCollections.observableArrayList();
            for (String description : serviceList) {
                serviceObservable.add(description);
            }
            selectServicesCombo.setItems(serviceObservable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void facilityComboLoad() {
        try {
            ArrayList<String> facilities = facilityBO.getAllFacilitiesDescriptions();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (String description : facilities) {
                observableList.add(description);
            }
            selectFacilitiesCombo.setItems(observableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCheckInDate() {
        checkInDate.setValue(LocalDate.parse(new SimpleDateFormat("YYYY-MM-dd").format(new Date())));
    }

    private void transition() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), reservationPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    private void nicAction(ActionEvent event) {
        if (checkNicValidation()) {
            String nic = nicText.getText();
            try {
                GuestDTO guestDTO = guestBO.searchGuestByNic(nic);
                if (guestDTO != null) {
                    guestIdText.setText(guestDTO.getGuestId() + "");
                    genderCombo.setValue(guestDTO.getGender());
                    guestNameText.setText(guestDTO.getGuestName());
                    telephoneText.setText(guestDTO.getTelephoneNo());
                    roomsCostText.requestFocus();
                    roomsCostText.selectAll();
                } else {
                    enableGuest();
                    loadGuestId();
                    loadGuestGender();
                    genderCombo.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkNicValidation() {
        boolean isNicValidate = Validatation.nicValidate(nicText.getText());
        if (isNicValidate) {
            return true;
        } else {
            alertMethod("NIC is Invalid!", nicText);
            return false;
        }
    }

    private void loadGuestGender() {
        try {
            ArrayList<String> guestGenders = guestBO.getGuestGender();
            for (String gender : guestGenders) {
                genderCombo.setItems(FXCollections.observableArrayList(gender));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGuestId() {
        try {
            ArrayList<Integer> guestIds = guestBO.getGuestIds();
            if (guestIds.isEmpty()) {
                guestIdText.setText(1 + "");
            } else {
                for (Integer id : guestIds) {
                    guestIdText.setText((++id) + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enableGuest() {
        genderCombo.setDisable(false);
        guestNameText.setDisable(false);
        telephoneText.setDisable(false);
    }

    @FXML
    private void selectFacilityAction(ActionEvent event) {
        facilityTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("facilityId"));
        facilityTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        facilityTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("facilityPrice"));
        try {
            String description = selectFacilitiesCombo.getValue();
            FacilityDTO facilityDTO = facilityBO.searchFacilityByDescription(description);
            facilityDTOS = FXCollections.observableArrayList();
            facilityDTOS.add(facilityDTO);
            facilityTable.setItems(facilityDTOS);
            getFacilityPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
        selectServicesCombo.requestFocus();
    }

    private void getFacilityPrice() {
        BigDecimal amountF = new BigDecimal(0);
        BigDecimal amountS = new BigDecimal(0);
        for (int i = 0; i < facilityTable.getItems().size(); i++) {
            if (!facilityTable.getItems().isEmpty()) {
                amountF = (BigDecimal) facilityTable.getColumns().get(2).getCellObservableValue(i).getValue();
            } else {
                amountF = new BigDecimal(0);
            }
            if (!serviceTable.getItems().isEmpty()) {
                amountS = (BigDecimal) serviceTable.getColumns().get(2).getCellObservableValue(i).getValue();
            } else {
                amountS = new BigDecimal(0);
            }
        }
        extrasText.setText("" + (amountF.add(amountS)));
    }

    @FXML
    private void removeFacilityButtonAction(ActionEvent event) {
        facilityTable.getItems().remove(facilityTable.getSelectionModel().getSelectedItem());
        selectFacilitiesCombo.getSelectionModel().clearSelection();
        getServicePrice();
    }

    @FXML
    private void selectServiceAction(ActionEvent event) {
        serviceTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        serviceTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        serviceTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        try {
            String description = selectServicesCombo.getValue();
            ServiceDTO serviceDTO = serviceBO.searchServiceByDescription(description);
            ObservableList<ServiceDTO> serviceDTOS = FXCollections.observableArrayList();
            serviceDTOS.add(serviceDTO);
            serviceTable.setItems(serviceDTOS);
            getServicePrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
        nicText.requestFocus();
    }

    private void getServicePrice() {
        BigDecimal amount1 = new BigDecimal(0);
        BigDecimal amount2 = new BigDecimal(0);
        for (int i = 0; i < serviceTable.getItems().size(); i++) {
            if (!serviceTable.getItems().isEmpty()) {
                amount1 = (BigDecimal) serviceTable.getColumns().get(2).getCellObservableValue(i).getValue();
            } else {
                amount1 = new BigDecimal(0);
            }

            if (!facilityTable.getItems().isEmpty()) {
                amount2 = (BigDecimal) facilityTable.getColumns().get(2).getCellObservableValue(i).getValue();
            } else {
                amount2 = new BigDecimal(0);
            }
        }
        extrasText.setText("" + (amount1.add(amount2)));
    }

    @FXML
    private void removeServiceButtonAction(ActionEvent event) {
        serviceTable.getItems().remove(serviceTable.getSelectionModel().getSelectedItem());
        selectServicesCombo.getSelectionModel().clearSelection();
        getFacilityPrice();
    }

    @FXML
    private void payButtonAction(ActionEvent event) {
        String reservationId = reservationIdText.getText();
        Date checkInD = localDateToDate(checkInDate.getValue());
        Time checkInT = localTimeToTime(checkInTime.getValue());
        Date checkOutD = localDateToDate(checkOutDate.getValue());
        int noOfAdult = Integer.parseInt(noOfAdultsText.getText());
        int guestId = Integer.parseInt(guestIdText.getText());
        BigDecimal reservationFee = new BigDecimal(billTotalLabel.getText());
        int paymentId = Integer.parseInt(paymentIdText.getText());

        ArrayList<Integer> roomIds = new ArrayList<>();
        for (RoomDTO roomDTO : bookingTable.getItems()) {
            roomIds.add(roomIdColumn.getCellObservableValue(roomDTO).getValue());
        }

        ArrayList<BigDecimal> roomPrices = new ArrayList<>();
        for (RoomDTO roomDTO : bookingTable.getItems()) {
            roomPrices.add(roomPriceColumn.getCellObservableValue(roomDTO).getValue());
        }

        ArrayList<RoomDetailDTO> roomList = new ArrayList<>();
        for (int i = 0; i < bookingTable.getItems().size(); i++) {
            int roomId = roomIds.get(i);
            BigDecimal roomPrice = roomPrices.get(i);
            RoomDetailDTO roomDetailDTO = new RoomDetailDTO(roomId, Integer.parseInt(reservationId), roomPrice);
            roomList.add(roomDetailDTO);
        }

        ArrayList<FacilityDetailDTO> facilityDetailList = new ArrayList<>();
        if (!facilityTable.getItems().isEmpty()) {
            for (int i = 0; i < facilityTable.getItems().size(); i++) {
                Integer facilityId = (Integer) facilityTable.getColumns().get(0).getCellObservableValue(i).getValue();
                BigDecimal facilityPrice = (BigDecimal) facilityTable.getColumns().get(2).getCellObservableValue(i).getValue();
                FacilityDetailDTO facilityDetailDTO = new FacilityDetailDTO(facilityId, Integer.parseInt(reservationId), facilityPrice);
                facilityDetailList.add(facilityDetailDTO);
            }
        } else {
            facilityDetailList.clear();
        }

        ArrayList<ServiceDetailDTO> serviceDetailList = new ArrayList<>();
        if (serviceTable.getItems().isEmpty()) {
            serviceDetailList.clear();
        } else {
            for (int i = 0; i < serviceTable.getItems().size(); i++) {
                Integer serviceId = (Integer) serviceTable.getColumns().get(0).getCellObservableValue(i).getValue();
                BigDecimal servicePrice = (BigDecimal) serviceTable.getColumns().get(2).getCellObservableValue(i).getValue();
                ServiceDetailDTO serviceDetailDTO = new ServiceDetailDTO(serviceId, Integer.parseInt(reservationId), servicePrice);
                serviceDetailList.add(serviceDetailDTO);
            }
        }

        GuestDTO guestDTO = new GuestDTO(Integer.parseInt(guestIdText.getText()), genderCombo.getSelectionModel().getSelectedItem(), guestNameText.getText(), nicText.getText(), telephoneText.getText());


        String reservationType = null;
        if (onPaymentRButton.isSelected()) {
            reservationType = "on payment";

            ArrayList<PaymentDTO> paymentList = new ArrayList<>();
            String paymetMethod = null;
            if (cashRButton.isSelected()) {
                paymetMethod = "cash";
                paymentList.add(new PaymentDTO(Integer.parseInt(reservationId), paymentId, paymetMethod, new BigDecimal(paidText.getText()), checkInD));

                ReservationDTO reservationDTO;

                reservationDTO = new ReservationDTO(Integer.parseInt(reservationId), checkInD, checkInT, checkOutD, noOfAdult, guestId, reservationType, reservationFee, roomList, facilityDetailList, serviceDetailList, guestDTO, paymentList);
                try {
                    boolean isAdded = reservationBO.addReservation(reservationDTO);
                    if (isAdded) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Reservation Done!", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        AnchorPane root = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/booking/reservation.fxml"));
                        reservationPane.getChildren().setAll(root);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (chequeRButton.isSelected()) {
                paymetMethod = "cheque";
                paymentList.add(new PaymentDTO(Integer.parseInt(reservationId), paymentId, paymetMethod, new BigDecimal(paidText.getText()), checkInD));
                ArrayList<ChequeDetailDTO> chequeDetailList = new ArrayList<>();
                chequeDetailList.add(new ChequeDetailDTO(paymentId, localDateToDate(dateOfExpireDate.getValue()), chequeNumberText.getText(), bankText.getText(), branchText.getText()));
                ReservationDTO reservationDTO = new ReservationDTO(Integer.parseInt(reservationId), checkInD, checkInT, checkOutD, noOfAdult, guestId, reservationType, reservationFee, roomList, facilityDetailList, serviceDetailList, guestDTO, paymentList, chequeDetailList);
                try {
                    boolean isAdded = reservationBO.addReservation(reservationDTO);
                    if (isAdded) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Reservation Done with Cheque Payment!", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        AnchorPane root = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/booking/reservation.fxml"));
                        reservationPane.getChildren().setAll(root);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            try {
                reservationType = "reserved";
                ArrayList<ReservedDTO> reservedDTOArrayList = new ArrayList<>();

                //get Reserved id
                int reservedId = 0;
                ArrayList<ReservedDTO> allReserveds = reservedBO.getAllReserveds();
                if (allReserveds.isEmpty()) {
                    reservedId = 1;
                } else {
                    ArrayList<Integer> reservedIds = new ArrayList<>();
                    for (ReservedDTO reservedDTO : allReserveds) {
                        reservedIds.add(reservedDTO.getReservedId());
                    }
                    for (Integer reservedIdInteger : reservedIds) {
                        reservedId = (++reservedIdInteger);
                    }
                }

                // get reserved date and Time
                LocalTime reservedTime = LocalTime.parse(new SimpleDateFormat("hh:mm:ss").format(new Date()));
                LocalDate reservedDate = LocalDate.parse(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));

                //adding reservedDTOArrayList
                reservedDTOArrayList.add(new ReservedDTO(reservedId, Integer.parseInt(reservationId), localDateToDate(reservedDate), localTimeToTime(reservedTime)));

                //making reservation arraylist
                ReservationDTO reservationDTO = new ReservationDTO(Integer.parseInt(reservationId), checkInD, checkInT, checkOutD, noOfAdult, guestId, reservationType, reservationFee, reservedDTOArrayList, roomList, facilityDetailList, serviceDetailList, guestDTO);


                boolean isAdded = reservationBO.addReservation(reservationDTO);
                if (isAdded) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Reserved Done!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                    AnchorPane root = FXMLLoader.load(getClass().getResource("/lk/ijse/roombooking/view/booking/reservation.fxml"));
                    reservationPane.getChildren().setAll(root);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private Date localDateToDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    private Time localTimeToTime(LocalTime time) {
        return java.sql.Time.valueOf(time);
    }

    @FXML
    private void telephoneNoAction(ActionEvent event) {

        roomsCostText.requestFocus();
        roomsCostText.selectAll();
    }

    private boolean validateGuestDetails() {
        System.out.println(genderCombo.getSelectionModel().getSelectedItem());
        if (Validatation.stringsValidate(genderCombo.getSelectionModel().getSelectedItem())) {
            boolean isValidateName = Validatation.nameValidate(guestNameText.getText());
            if (isValidateName) {
                boolean isValidateTelephoneNo = Validatation.telephoneNoValidate(telephoneText.getText());
                if (isValidateTelephoneNo) {
                    return true;
                } else {
                    alertMethod("Telephone no is Invalid!", telephoneText);
                    telephoneText.selectAll();
                    return false;
                }
            } else {
                alertMethod("Guest name is Invalid!", guestNameText);
                guestNameText.selectAll();
                return false;
            }
        } else {
            alertMethod("Gender is invalid!", genderCombo);
            return false;
        }
    }

    private boolean checkGuestDetails() {
        String name = guestNameText.getText();
        String telephone = telephoneText.getText();
        if (genderCombo.getValue() == null) {
            genderCombo.setValue("mr");
        }
        if (name.trim().isEmpty()) {
            alertMethod("Guest Name is Empty!", guestNameText);
            return false;
        } else if (telephone.trim().isEmpty()) {
            alertMethod("Telephone No is Required!", telephoneText);
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private void chequeRAction(ActionEvent event) {
        chequeNumberText.setDisable(false);
        dateOfExpireDate.setDisable(false);
        bankText.setDisable(false);
        branchText.setDisable(false);
        chequeNumberText.requestFocus();

    }

    @FXML
    private void cashRAction(ActionEvent event) {
        chequeNumberText.setDisable(true);
        dateOfExpireDate.setDisable(true);
        bankText.setDisable(true);
        branchText.setDisable(true);
        roomsCostText.requestFocus();
        roomsCostText.selectAll();
    }

    @FXML
    private void reservingRAction(ActionEvent event) {
        paidText.setDisable(true);
        payButton.setText("Reserve");
    }


    @FXML
    private void searchButtonAction(ActionEvent event) {
        if (checkRoomsFields()) {
            if (checkValidationForSearchRooms()) {
                String roomCategory = roomCategoryCombo.getValue();
                String roomType = roomTypeCombo.getValue();
                String bedType = bedTypeCombo.getValue();
                int noAdults = Integer.parseInt(noOfAdultsText.getText());
                try {
                    ArrayList<RoomDTO> roomList = roomBO.getRoomsForReservation(roomCategory, roomType, bedType);
                    if (roomList.size() < noAdults / 2 || roomList.size() > noAdults) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Rooms are not enough!", ButtonType.OK);
                        a.setHeaderText(null);
                        a.showAndWait();
                        noOfAdultsText.requestFocus();
                    } else {
                        bookingTable.setItems(FXCollections.observableArrayList());
                        int column = 0;
                        int row = 0;
                        for (RoomDTO room : roomList) {
                            JFXButton roomButton = new JFXButton("Room ID : " + room.getRoomId() + "\n" + "Room Floor : " + room.getRoomFloor());
                            roomButton.setMaxWidth(Double.MAX_VALUE);
                            roomButton.setMaxHeight(Double.MAX_VALUE);
                            roomButton.setStyle("-fx-background-color: GREEN");
                            roomButtonGridPane.setFillWidth(roomButton, true);
                            roomButtonGridPane.setFillHeight(roomButton, true);
                            roomButtonGridPane.add(roomButton, column, row);

                            loadColumns();

                            EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        roomButton.setStyle("-fx-background-color: RED");
                                        RoomDTO roomDTOS = roomBO.searchRoom(room.getRoomId());
                                        data.add(roomDTOS);
                                        bookingTable.setItems(data);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            roomButton.setOnAction(buttonHandler);
                            column++;
                            if (getColumnsOfGridPane() == column) {
                                row++;
                            }
                        }
                        selectRoomsPane.setVisible(true);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean checkValidationForSearchRooms() {

        if (!checkOutDate.getValue().isAfter(checkInDate.getValue())) {
            alertMethod("Check Out Date is Invalid!", checkOutDate);
            return false;
        } else {
            boolean isValidateNoOfAdults = Validatation.integerValueValidate(noOfAdultsText.getText());
            if (isValidateNoOfAdults) {
                return true;
            } else {
                alertMethod("No of Adults must be numeric value!", noOfAdultsText);
                noOfAdultsText.selectAll();
                return false;
            }
        }

    }

    private void loadColumns() {
        bookingTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        bookingTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomCategory"));
        bookingTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        bookingTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("bedType"));
        bookingTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("roomFloor"));
        bookingTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
    }

    @FXML
    private void removeButtonAction(ActionEvent event) {
        RoomDTO roomDTO = bookingTable.getSelectionModel().getSelectedItem();
        BigDecimal removePrice = roomDTO.getRoomPrice();
        BigDecimal roomCostValues = new BigDecimal(roomsCostText.getText());
        roomCostValues = roomCostValues.subtract(removePrice);
        roomsCostText.setText(roomCostValues + "");
        bookingTable.getItems().remove(bookingTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onPaymentRAction(ActionEvent event) {
        paidText.setDisable(false);
        payButton.setText("Pay");
        payButton.setDisable(true);
    }

    @FXML
    private void checkOutDateAction(ActionEvent event) {
        long checkIn = checkInDate.getValue().toEpochDay();
        long checkOut = checkOutDate.getValue().toEpochDay();
        int days = (int) Math.abs(checkOut - checkIn);

        noOfNightsLabel.setText("No Of Nights : " + days);
        noOfAdultsText.requestFocus();
    }

    @FXML
    private void doneButtonAction(ActionEvent event) {
        if (bookingTable.getItems().size() != 0) {
            selectRoomsPane.setVisible(false);
            BigDecimal amount = new BigDecimal(0);

            ArrayList<BigDecimal> roomPrices = new ArrayList<>();
            for (RoomDTO roomDTO : bookingTable.getItems()) {
                roomPrices.add(roomPriceColumn.getCellObservableValue(roomDTO).getValue());
            }

            for (BigDecimal bigDecimal : roomPrices) {
                amount = amount.add(bigDecimal);
            }
            long checkIn = checkInDate.getValue().toEpochDay();
            long checkOut = checkOutDate.getValue().toEpochDay();
            BigDecimal days = BigDecimal.valueOf(Math.abs(checkOut - checkIn));
            if (days.equals(new BigDecimal(0))) {
                days.add(new BigDecimal(1));
            }
            amount = amount.multiply(days);

            roomsCostText.setText("" + amount);
            selectFacilitiesCombo.requestFocus();
        } else {
            alertMethod("You must book a room!", checkOutDate);
        }
    }

    @FXML
    private void extrasTextAction(ActionEvent event) {
        BigDecimal roomsCost = new BigDecimal(roomsCostText.getText());
        BigDecimal extrasCost = new BigDecimal(extrasText.getText());
        billTotalLabel.setText("" + roomsCost.add(extrasCost));
        if (reservingRButton.isSelected()) {
            payButton.setDisable(false);
        }
        paidText.requestFocus();
    }

    @FXML
    private void paidTextAction(ActionEvent event) {

        if (paidText.getText().trim().isEmpty()) {
            alertMethod("Please Pay to Reservation!", paidText);
        } else {
            if (validatePaidText()) {
                BigDecimal paidCost = new BigDecimal(paidText.getText());
                BigDecimal totalCost = new BigDecimal(billTotalLabel.getText());
                if (paidCost.compareTo(totalCost.divide(new BigDecimal(2))) >= 0) {
                    payButton.setDisable(false);
                    balanceLabel.setText(paidCost.subtract(totalCost) + "");
                } else {
                    alertMethod("You must paid at least half of reservation fee!", paidText);
                    paidText.selectAll();
                }
            }
        }
    }

    private boolean validatePaidText() {
        if (Validatation.pricesValidate(paidText.getText())) {
            return true;
        } else {
            alertMethod("Paying price is Invalid!", paidText);
            paidText.selectAll();
            return false;
        }
    }

    private int getRowsOfGridPane() {
        int rows = 0;
        try {
            Method rowMethod = roomButtonGridPane.getClass().getDeclaredMethod("getNumberOfRows");
            rowMethod.setAccessible(true);
            rows = (int) rowMethod.invoke(roomButtonGridPane) - 1;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return rows;
    }

    private int getColumnsOfGridPane() {
        int columns = 0;
        try {
            Method columnMethod = roomButtonGridPane.getClass().getDeclaredMethod("getNumberOfColumns");
            columnMethod.setAccessible(true);
            columns = (int) columnMethod.invoke(roomButtonGridPane) - 1;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return columns;
    }

    @FXML
    private void guestNameAction(ActionEvent event) {
        telephoneText.requestFocus();
    }

    @FXML
    private void noOfAdultsAction(ActionEvent event) {
        roomCategoryCombo.requestFocus();
    }

    @FXML
    private void roomCategoryComboAction(ActionEvent event) {
        roomTypeCombo.requestFocus();
    }

    @FXML
    private void roomTypeComboAction(ActionEvent event) {
        bedTypeCombo.requestFocus();
    }

    @FXML
    private void bedTypeComboAction(ActionEvent event) {
        if (checkRoomsFields()) {
            searchButtonAction(event);
        }
    }

    private boolean checkRoomsFields() {
        LocalDate date = checkOutDate.getValue();
        String noOfAdults = noOfAdultsText.getText();
        String categoryComboValue = roomCategoryCombo.getValue();
        String roomTypeComboValue = roomTypeCombo.getValue();
        String bedTypeComboValue = bedTypeCombo.getValue();
        if (date == null) {
            alertMethod("CheckOutDate can not be Empty!", checkOutDate);
            return false;
        } else if (noOfAdults == null) {
            alertMethod("CheckOutDate can not be Empty!", noOfAdultsText);
            return false;
        } else if (categoryComboValue == null) {
            alertMethod("Please Select Room Category!", roomCategoryCombo);
            return false;
        } else if (roomTypeComboValue == null) {
            alertMethod("Please Select Room Type!", roomTypeCombo);
            return false;
        } else if (bedTypeComboValue == null) {
            alertMethod("Please Select Bed Type!", bedTypeCombo);
            return false;
        } else {
            return true;
        }
    }

    private void alertMethod(String msg, Control controller) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
        controller.requestFocus();
    }


    @FXML
    private void roomsCostTextAction(ActionEvent event) {
        if (facilityTable.getItems().isEmpty() && serviceTable.getItems().isEmpty()) {
            extrasText.setText(new BigDecimal(0) + "");
        }
        extrasText.requestFocus();
        extrasText.selectAll();
    }

}
