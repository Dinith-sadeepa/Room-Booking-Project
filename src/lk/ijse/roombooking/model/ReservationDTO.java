package lk.ijse.roombooking.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class ReservationDTO {
    private int reservationId;
    private Date checkInDate;
    private Time checkInTime;
    private Date checkOutDate;
    private int noOfAdults;
    private int guestId;
    private String reservationType;
    private BigDecimal reservationFee;
    private ArrayList<ReservedDTO> reservedDetailList;
    private ArrayList<RoomDetailDTO> roomDetailList;
    private ArrayList<FacilityDetailDTO> facilityDetailList;
    private ArrayList<ServiceDetailDTO> serviceDetailList;
    private GuestDTO guestDTO;
    private ArrayList<PaymentDTO> paymentList;
    private ArrayList<ChequeDetailDTO> chequeDetailList;


    public ReservationDTO() {
    }

    public ReservationDTO(int reservationId, Date checkInDate, Time checkInTime, Date checkOutDate, int noOfAdults, int guestId, String reservationType, BigDecimal reservationFee) {
        this.reservationId = reservationId;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.checkOutDate = checkOutDate;
        this.noOfAdults = noOfAdults;
        this.guestId = guestId;
        this.reservationType = reservationType;
        this.reservationFee = reservationFee;
    }

    public ReservationDTO(int reservationId, Date checkInDate, Time checkInTime, Date checkOutDate, int noOfAdults, int guestId, String reservationType, BigDecimal reservationFee, ArrayList<RoomDetailDTO> roomDetailList, ArrayList<FacilityDetailDTO> facilityDetailList, ArrayList<ServiceDetailDTO> serviceDetailList, GuestDTO guestDTO, ArrayList<PaymentDTO> paymentList) {
        this.reservationId = reservationId;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.checkOutDate = checkOutDate;
        this.noOfAdults = noOfAdults;
        this.guestId = guestId;
        this.reservationType = reservationType;
        this.reservationFee = reservationFee;
        this.roomDetailList = roomDetailList;
        this.facilityDetailList = facilityDetailList;
        this.serviceDetailList = serviceDetailList;
        this.guestDTO = guestDTO;
        this.paymentList = paymentList;
    }

    public ReservationDTO(int reservationId, Date checkInDate, Time checkInTime, Date checkOutDate, int noOfAdults, int guestId, String reservationType, BigDecimal reservationFee, ArrayList<RoomDetailDTO> roomDetailList, ArrayList<FacilityDetailDTO> facilityDetailList, ArrayList<ServiceDetailDTO> serviceDetailList, GuestDTO guestDTO, ArrayList<PaymentDTO> paymentList, ArrayList<ChequeDetailDTO> chequeDetailList) {
        this.reservationId = reservationId;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.checkOutDate = checkOutDate;
        this.noOfAdults = noOfAdults;
        this.guestId = guestId;
        this.reservationType = reservationType;
        this.reservationFee = reservationFee;
        this.roomDetailList = roomDetailList;
        this.facilityDetailList = facilityDetailList;
        this.serviceDetailList = serviceDetailList;
        this.guestDTO = guestDTO;
        this.paymentList = paymentList;
        this.chequeDetailList = chequeDetailList;
    }

    public ReservationDTO(int reservationId, Date checkInDate, Time checkInTime, Date checkOutDate, int noOfAdults, int guestId, String reservationType, BigDecimal reservationFee, ArrayList<ReservedDTO> reservedDetailList, ArrayList<RoomDetailDTO> roomDetailList, ArrayList<FacilityDetailDTO> facilityDetailList, ArrayList<ServiceDetailDTO> serviceDetailList, GuestDTO guestDTO) {
        this.reservationId = reservationId;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.checkOutDate = checkOutDate;
        this.noOfAdults = noOfAdults;
        this.guestId = guestId;
        this.reservationType = reservationType;
        this.reservationFee = reservationFee;
        this.reservedDetailList = reservedDetailList;
        this.roomDetailList = roomDetailList;
        this.facilityDetailList = facilityDetailList;
        this.serviceDetailList = serviceDetailList;
        this.guestDTO = guestDTO;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Time getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Time checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public BigDecimal getReservationFee() {
        return reservationFee;
    }

    public void setReservationFee(BigDecimal reservationFee) {
        this.reservationFee = reservationFee;
    }

    public ArrayList<ReservedDTO> getReservedDetailList() {
        return reservedDetailList;
    }

    public void setReservedDetailList(ArrayList<ReservedDTO> reservedDetailList) {
        this.reservedDetailList = reservedDetailList;
    }

    public ArrayList<RoomDetailDTO> getRoomDetailList() {
        return roomDetailList;
    }

    public void setRoomDetailList(ArrayList<RoomDetailDTO> roomDetailList) {
        this.roomDetailList = roomDetailList;
    }

    public ArrayList<FacilityDetailDTO> getFacilityDetailList() {
        return facilityDetailList;
    }

    public void setFacilityDetailList(ArrayList<FacilityDetailDTO> facilityDetailList) {
        this.facilityDetailList = facilityDetailList;
    }

    public ArrayList<ServiceDetailDTO> getServiceDetailList() {
        return serviceDetailList;
    }

    public void setServiceDetailList(ArrayList<ServiceDetailDTO> serviceDetailList) {
        this.serviceDetailList = serviceDetailList;
    }

    public GuestDTO getGuestDTO() {
        return guestDTO;
    }

    public void setGuestDTO(GuestDTO guestDTO) {
        this.guestDTO = guestDTO;
    }

    public ArrayList<PaymentDTO> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(ArrayList<PaymentDTO> paymentList) {
        this.paymentList = paymentList;
    }

    public ArrayList<ChequeDetailDTO> getChequeDetailList() {
        return chequeDetailList;
    }

    public void setChequeDetailList(ArrayList<ChequeDetailDTO> chequeDetailList) {
        this.chequeDetailList = chequeDetailList;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "reservationId=" + reservationId +
                ", checkInDate=" + checkInDate +
                ", checkInTime=" + checkInTime +
                ", checkOutDate=" + checkOutDate +
                ", noOfAdults=" + noOfAdults +
                ", guestId=" + guestId +
                ", reservationType='" + reservationType + '\'' +
                ", reservationFee=" + reservationFee +
                ", reservedDetailList=" + reservedDetailList +
                ", roomDetailList=" + roomDetailList +
                ", facilityDetailList=" + facilityDetailList +
                ", serviceDetailList=" + serviceDetailList +
                ", guestDTO=" + guestDTO +
                ", paymentList=" + paymentList +
                ", chequeDetailList=" + chequeDetailList +
                '}';
    }
}
