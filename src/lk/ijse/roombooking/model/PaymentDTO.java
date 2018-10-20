package lk.ijse.roombooking.model;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentDTO {
    private int reservationId;
    private int paymentId;
    private String payemntMethod;
    private BigDecimal amount;
    private Date date;

    public PaymentDTO() {
    }

    public PaymentDTO(int reservationId, int paymentId, String payemntMethod, BigDecimal amount, Date date) {
        this.reservationId = reservationId;
        this.paymentId = paymentId;
        this.payemntMethod = payemntMethod;
        this.amount = amount;
        this.date = date;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayemntMethod() {
        return payemntMethod;
    }

    public void setPayemntMethod(String payemntMethod) {
        this.payemntMethod = payemntMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "reservationId=" + reservationId +
                ", paymentId=" + paymentId +
                ", payemntMethod='" + payemntMethod + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
