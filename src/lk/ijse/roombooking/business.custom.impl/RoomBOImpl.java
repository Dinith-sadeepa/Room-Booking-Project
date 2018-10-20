package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.RoomBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.RoomDAO;
import lk.ijse.roombooking.entity.Room;
import lk.ijse.roombooking.model.RoomDTO;

import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {

    private RoomDAO roomDAO;

    public RoomBOImpl() {
        this.roomDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
    }

    @Override
    public boolean addRoom(RoomDTO room) throws Exception {
        return roomDAO.save(new Room(room.getRoomId(), room.getBedType(), room.getRoomType(), room.getRoomCategory(), room.getRoomFloor(), room.getRoomPrice(), "available"));
    }

    @Override
    public boolean deleteRoom(int roomId) throws Exception {
        return roomDAO.delete(roomId);
    }

    @Override
    public boolean updateRoom(RoomDTO room) throws Exception {
        return roomDAO.update(new Room(room.getRoomId(), room.getBedType(), room.getRoomType(), room.getRoomCategory(), room.getRoomFloor(), room.getRoomPrice(), room.getStatus()));
    }

    @Override
    public RoomDTO searchRoom(int roomId) throws Exception {
        Room room = roomDAO.search(roomId);
        return new RoomDTO(room.getRoomId(), room.getBedType(), room.getRoomType(), room.getRoomCategory(), room.getRoomFloor(), room.getRoomPrice(), room.getStatus());
    }

    @Override
    public RoomDTO searchRoomForReservation(String roomCategory, String roomType, String bedType) throws Exception {
        return null;
    }

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws Exception {
        ArrayList<Room> roomList = roomDAO.getAll();
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : roomList) {
            roomDTOS.add(new RoomDTO(r.getRoomId(), r.getBedType(), r.getRoomType(), r.getRoomCategory(), r.getRoomFloor(), r.getRoomPrice(), r.getStatus()));
        }
        return roomDTOS;
    }

    @Override
    public ArrayList<RoomDTO> searchRoomForDeparting(Integer reservationId) throws Exception {
        ArrayList<Room> roomArrayList = roomDAO.getReservedRoomByReservationId(reservationId);
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : roomArrayList) {
            roomDTOS.add(new RoomDTO(r.getRoomId(), r.getBedType(), r.getRoomType(), r.getRoomCategory(), r.getRoomFloor(), r.getRoomPrice(), r.getStatus()));
        }
        return roomDTOS;
    }

    @Override
    public ArrayList<String> getRoomCategories() throws Exception {
        return roomDAO.getRoomCategory();
    }

    @Override
    public ArrayList<RoomDTO> getRoomsForReservation(String roomCategory, String roomType, String bedType) throws Exception {
        ArrayList<Room> roomArrayList = roomDAO.getRoomsForReservation(roomCategory, roomType, bedType);
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : roomArrayList) {
            roomDTOS.add(new RoomDTO(r.getRoomId(), r.getBedType(), r.getRoomType(), r.getRoomCategory(), r.getRoomFloor(), r.getRoomPrice(), r.getStatus()));
        }
        return roomDTOS;
    }

    @Override
    public ArrayList<String> getRoomTypes() throws Exception {
        return roomDAO.getRoomType();
    }

    @Override
    public ArrayList<String> getBedTypes() throws Exception {
        return roomDAO.getBedType();
    }

    @Override
    public ArrayList<Integer> getRoomIds() throws Exception {
        ArrayList<Room> roomArrayList = roomDAO.getAll();
        ArrayList<Integer> roomIds = new ArrayList<>();
        for (Room room : roomArrayList) {
            roomIds.add(room.getRoomId());
        }
        return roomIds;
    }
}
