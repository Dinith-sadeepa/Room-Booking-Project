package lk.ijse.roombooking.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class Departing {
    private int departingId;
    private int reservationId;
    private Date checkOutDate;
    private Time checkOutTime;

    public Departing() {
    }

    public Departing(int departingId, int reservationId, Date checkOutDate, Time checkOutTime) {
        this.departingId = departingId;
        this.reservationId = reservationId;
        this.checkOutDate = checkOutDate;
        this.checkOutTime = checkOutTime;
    }

    public int getDepartingId() {
        return departingId;
    }

    public void setDepartingId(int departingId) {
        this.departingId = departingId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Time getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Time checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
