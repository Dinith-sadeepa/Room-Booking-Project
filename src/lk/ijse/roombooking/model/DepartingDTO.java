package lk.ijse.roombooking.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class DepartingDTO {
    private int departingId;
    private int reservationId;
    private Date checkOutDate;
    private Time checkOutTime;
    private ArrayList<PaymentDTO> paymentList;
    private ArrayList<ChequeDetailDTO> chequeDetailList;

    public DepartingDTO() {
    }

    public DepartingDTO(int departingId, int reservationId, Date checkOutDate, Time checkOutTime) {
        this.departingId = departingId;
        this.reservationId = reservationId;
        this.checkOutDate = checkOutDate;
        this.checkOutTime = checkOutTime;
    }

    public DepartingDTO(int departingId, int reservationId, Date checkOutDate, Time checkOutTime, ArrayList<PaymentDTO> paymentList) {
        this.departingId = departingId;
        this.reservationId = reservationId;
        this.checkOutDate = checkOutDate;
        this.checkOutTime = checkOutTime;
        this.paymentList = paymentList;
    }

    public DepartingDTO(int departingId, int reservationId, Date checkOutDate, Time checkOutTime, ArrayList<PaymentDTO> paymentList, ArrayList<ChequeDetailDTO> chequeDetailList) {
        this.departingId = departingId;
        this.reservationId = reservationId;
        this.checkOutDate = checkOutDate;
        this.checkOutTime = checkOutTime;
        this.paymentList = paymentList;
        this.chequeDetailList = chequeDetailList;
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
        return "DepartingDTO{" +
                "departingId=" + departingId +
                ", reservationId=" + reservationId +
                ", checkOutDate=" + checkOutDate +
                ", checkOutTime=" + checkOutTime +
                ", paymentList=" + paymentList +
                ", chequeDetailList=" + chequeDetailList +
                '}';
    }
}
