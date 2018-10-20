package lk.ijse.roombooking.model;

import java.sql.Time;
import java.util.Date;

public class ReservedDTO {
    private int reservedId;
    private int reservationId;
    private Date reservedDate;
    private Time reservedTime;

    public ReservedDTO() {
    }

    public ReservedDTO(int reservedId, int reservationId, Date reservedDate, Time reservedTime) {
        this.reservedId = reservedId;
        this.reservationId = reservationId;
        this.reservedDate = reservedDate;
        this.reservedTime = reservedTime;
    }

    public int getReservedId() {
        return reservedId;
    }

    public void setReservedId(int reservedId) {
        this.reservedId = reservedId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Date getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(Date reservedDate) {
        this.reservedDate = reservedDate;
    }

    public Time getReservedTime() {
        return reservedTime;
    }

    public void setReservedTime(Time reservedTime) {
        this.reservedTime = reservedTime;
    }

    @Override
    public String toString() {
        return "ReservedDTO{" +
                "reservedId=" + reservedId +
                ", reservationId=" + reservationId +
                ", reservedDate=" + reservedDate +
                ", reservedTime=" + reservedTime +
                '}';
    }
}
