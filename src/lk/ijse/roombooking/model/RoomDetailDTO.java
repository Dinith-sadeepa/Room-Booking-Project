package lk.ijse.roombooking.model;

import java.math.BigDecimal;

public class RoomDetailDTO {
    private int roomDetailId;
    private int roomId;
    private int reservationId;
    private BigDecimal roomPrice;

    public RoomDetailDTO() {
    }

    public RoomDetailDTO(int roomId, int reservationId, BigDecimal roomPrice) {
        this.roomId = roomId;
        this.reservationId = reservationId;
        this.roomPrice = roomPrice;
    }

    public RoomDetailDTO(int roomDetailId, int roomId, int reservationId, BigDecimal roomPrice) {
        this.roomDetailId = roomDetailId;
        this.roomId = roomId;
        this.reservationId = reservationId;
        this.roomPrice = roomPrice;
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

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    @Override
    public String toString() {
        return "RoomDetailDTO{" +
                "roomDetailId=" + roomDetailId +
                ", roomId=" + roomId +
                ", reservationId=" + reservationId +
                ", roomPrice=" + roomPrice +
                '}';
    }
}
