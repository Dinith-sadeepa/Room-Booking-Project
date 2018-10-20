package lk.ijse.roombooking.entity;

import java.math.BigDecimal;

public class Room {
    private int roomId;
    private String bedType;
    private String roomType;
    private String roomCategory;
    private int roomFloor;
    private BigDecimal roomPrice;
    private String status;

    public Room() {
    }

    public Room(int roomId, String bedType, String roomType, String roomCategory, int roomFloor, BigDecimal roomPrice, String status) {
        this.roomId = roomId;
        this.bedType = bedType;
        this.roomType = roomType;
        this.roomCategory = roomCategory;
        this.roomFloor = roomFloor;
        this.roomPrice = roomPrice;
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
