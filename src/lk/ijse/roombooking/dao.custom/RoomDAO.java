package lk.ijse.roombooking.dao.custom;

import lk.ijse.roombooking.dao.CrudDAO;
import lk.ijse.roombooking.entity.Room;

import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<Room, Integer> {

    ArrayList<Room> getReservedRoomByReservationId(Integer reservationId) throws Exception;

    Room search(int roomId) throws Exception;

    Room searchRoomForReservation(String roomCategory, String roomType, String bedType) throws Exception;

    ArrayList<Room> getRoomsForReservation(String roomCategory, String roomType, String bedType) throws Exception;

    ArrayList<String> getRoomType() throws Exception;

    ArrayList<String> getRoomCategory() throws Exception;

    ArrayList<String> getBedType() throws Exception;
}
