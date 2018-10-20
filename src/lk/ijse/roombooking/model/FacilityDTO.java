package lk.ijse.roombooking.model;

import java.math.BigDecimal;

public class FacilityDTO {
    private int facilityId;
    private String description;
    private BigDecimal facilityPrice;

    public FacilityDTO() {
    }

    public FacilityDTO(int facilityId, String description, BigDecimal facilityPrice) {
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

    @Override
    public String toString() {
        return "FacilityDTO{" +
                "facilityId=" + facilityId +
                ", description='" + description + '\'' +
                ", facilityPrice=" + facilityPrice +
                '}';
    }
}
