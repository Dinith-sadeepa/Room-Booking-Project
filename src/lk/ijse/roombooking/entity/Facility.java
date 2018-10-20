package lk.ijse.roombooking.entity;

import java.math.BigDecimal;

public class Facility {
    private int facilityId;
    private String description;
    private BigDecimal facilityPrice;

    public Facility() {
    }

    public Facility(int facilityId, String description, BigDecimal facilityPrice) {
        this.facilityId = facilityId;
        this.description = description;
        this.facilityPrice = facilityPrice;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getFacilityPrice() {
        return facilityPrice;
    }

    public void setFacilityPrice(BigDecimal facilityPrice) {
        this.facilityPrice = facilityPrice;
    }
}
