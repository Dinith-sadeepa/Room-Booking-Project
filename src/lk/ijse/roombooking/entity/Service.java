package lk.ijse.roombooking.entity;

import java.math.BigDecimal;

public class Service {
    private int serviceId;
    private String description;
    private BigDecimal servicePrice;

    public Service() {
    }

    public Service(int serviceId, String description, BigDecimal servicePrice) {
        this.serviceId = serviceId;
        this.description = description;
        this.servicePrice = servicePrice;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }
}
