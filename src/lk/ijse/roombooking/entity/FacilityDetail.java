package lk.ijse.roombooking.entity;

import java.math.BigDecimal;

public class FacilityDetail {
    private int facilityDetailId;
    private int facilityId;
    private int reservationId;
    private BigDecimal price;

    public FacilityDetail() {
    }

    public FacilityDetail(int facilityId, int reservationId, BigDecimal price) {
        this.facilityId = facilityId;
        this.reservationId = reservationId;
        this.price = price;
    }

    public FacilityDetail(int facilityDetailId, int facilityId, int reservationId, BigDecimal price) {
        this.facilityDetailId = facilityDetailId;
        this.facilityId = facilityId;
        this.reservationId = reservationId;
        this.price = price;
    }

    public int getFacilityDetailId() {
        return facilityDetailId;
    }

    public void setFacilityDetailId(int facilityDetailId) {
        this.facilityDetailId = facilityDetailId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
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
