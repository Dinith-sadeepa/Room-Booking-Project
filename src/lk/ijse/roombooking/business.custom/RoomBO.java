package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.RoomDTO;

import java.util.ArrayList;

public interface RoomBO extends SuperBO {
    public boolean addRoom(RoomDTO room) throws Exception;

    public boolean deleteRoom(int roomId) throws Exception;

    public boolean updateRoom(RoomDTO room) throws Exception;

    public RoomDTO searchRoom(int roomId) throws Exception;

    public RoomDTO searchRoomForReservation(String roomCategory, String roomType, String bedType) throws Exception;

    public ArrayList<RoomDTO> getAllRoom() throws Exception;


    ArrayList<RoomDTO> searchRoomForDeparting(Integer reservationId) throws Exception;

    public ArrayList<String> getRoomCategories() throws Exception;

    ArrayList<RoomDTO> getRoomsForReservation(String roomCategory, String roomType, String bedType) throws Exception;

    public ArrayList<String> getRoomTypes() throws Exception;

    public ArrayList<String> getBedTypes() throws Exception;

    ArrayList<Integer> getRoomIds() throws Exception;
}
