package lk.ijse.roombooking.model;

import java.math.BigDecimal;

public class FacilityDetailDTO {
    private int facilityDetailId;
    private int facilityId;
    private int reservationId;
    private BigDecimal price;

    public FacilityDetailDTO() {
    }

    public FacilityDetailDTO(int facilityId, int reservationId, BigDecimal price) {
        this.facilityId = facilityId;
        this.reservationId = reservationId;
        this.price = price;
    }

    public FacilityDetailDTO(int facilityDetailId, int facilityId, int reservationId, BigDecimal price) {
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

    @Override
    public String toString() {
        return "FacilityDetailDTO{" +
                "facilityDetailId=" + facilityDetailId +
                ", facilityId=" + facilityId +
                ", reservationId=" + reservationId +
                ", price=" + price +
                '}';
    }
}
