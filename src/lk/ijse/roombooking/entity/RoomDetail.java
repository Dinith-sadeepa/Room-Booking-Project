package lk.ijse.roombooking.entity;

import java.math.BigDecimal;

public class RoomDetail {
    private int roomDetailId;
    private int roomId;
    private int reservationId;
    private BigDecimal price;

    public RoomDetail() {
    }

    public RoomDetail(int roomId, int reservationId, BigDecimal price) {
        this.roomId = roomId;
        this.reservationId = reservationId;
        this.price = price;
    }

    public RoomDetail(int roomDetailId, int roomId, int reservationId, BigDecimal price) {
        this.roomDetailId = roomDetailId;
        this.roomId = roomId;
        this.reservationId = reservationId;
        this.price = price;
    }

    public int getRoomDetailId() {
        return roomDetailId;
    }

    public void setRoomDetailId(int roomDetailId) {
        this.roomDetailId = roomDetailId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
