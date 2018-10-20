package lk.ijse.roombooking.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class Reservation {
    private int reservationId;
    private Date checkInDate;
    private Time checkInTime;
    private Date checkOutDate;
    private int noOfAdults;
    private int guestId;
    private String reservationType;
    private BigDecimal reservationFee;

    public Reservation() {
    }

    public Reservation(int reservationId, Date checkInDate, Time checkInTime, Date checkOutDate, int noOfAdults, int guestId, String reservationType, BigDecimal reservationFee) {
        this.reservationId = reservationId;
        this.checkInDate = checkInDate;
        this.checkInTime = checkInTime;
        this.checkOutDate = checkOutDate;
        this.noOfAdults = noOfAdults;
        this.guestId = guestId;
        this.reservationType = reservationType;
        this.reservationFee = reservationFee;
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
}
