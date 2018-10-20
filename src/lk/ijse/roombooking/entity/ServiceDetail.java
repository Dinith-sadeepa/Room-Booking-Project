package lk.ijse.roombooking.entity;

import java.math.BigDecimal;

public class ServiceDetail {
    private int serviceDetailId;
    private int serviceId;
    private int reservationId;
    private BigDecimal price;

    public ServiceDetail() {
    }

    public ServiceDetail(int serviceId, int reservationId, BigDecimal price) {
        this.serviceId = serviceId;
        this.reservationId = reservationId;
        this.price = price;
    }

    public ServiceDetail(int serviceDetailId, int serviceId, int reservationId, BigDecimal price) {
        this.serviceDetailId = serviceDetailId;
        this.serviceId = serviceId;
        this.reservationId = reservationId;
        this.price = price;
    }

    public int getServiceDetailId() {
        return serviceDetailId;
    }

    public void setServiceDetailId(int serviceDetailId) {
        this.serviceDetailId = serviceDetailId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
