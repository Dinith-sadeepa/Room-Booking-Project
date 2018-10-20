package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.RoomDAO;
import lk.ijse.roombooking.entity.Room;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room room) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Room VALUES(?,?,?,?,?,?,?)", room.getRoomId(), room.getBedType(), room.getRoomType(), room.getRoomCategory(), room.getRoomFloor(), room.getRoomPrice(), room.getStatus()) > 0;
    }

    @Override
    public boolean update(Room room) throws Exception {
        return CrudUtil.executeUpdate("Update Room set bedType = ? , roomType = ? , roomCategory = ? , roomFloor = ? , roomPrice = ? , status = ? where roomId = ? ", room.getBedType(), room.getRoomType(), room.getRoomCategory(), room.getRoomFloor(), room.getRoomPrice(), room.getStatus(), room.getRoomId()) > 0;
    }

    @Override
    public boolean delete(Integer roomId) throws Exception {
        return CrudUtil.executeUpdate("Delete from Room where roomId = ?", roomId) > 0;
    }

    @Override
    public Room search(Integer integer) throws Exception {
        return null;
    }


    @Override
    public ArrayList<Room> getReservedRoomByReservationId(Integer reservationId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select r.roomId , bedType , roomType , roomCategory , roomFloor , roomPrice , status from room r , roomDetail rd , reservation res where res.reservationId = rd.reservationId AND rd.roomId = r.roomId AND rd.reservationId = ?", reservationId);
        ArrayList<Room> rooms = new ArrayList<>();
        while (rst.next()) {
            rooms.add(new Room(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getBigDecimal(6),
                    rst.getString(7)));
        }
        return rooms;
    }

    @Override
    public Room search(int roomId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Room where roomId = ?", roomId);
        if (rst.next()) {
            return new Room(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), Integer.parseInt(rst.getString(5)), rst.getBigDecimal(6), rst.getString(7));
        } else {
            return null;
        }
    }

    @Override
    public Room searchRoomForReservation(String roomCategory, String roomType, String bedType) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Room where bedType = ? AND roomType = ? AND roomCategory = ?", bedType, roomType, roomCategory);
        if (rst.next()) {
            return new Room(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getBigDecimal(6), rst.getString(7));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Room> getRoomsForReservation(String roomCategory, String roomType, String bedType) throws Exception {
        String available = "Available";
        ResultSet rst = CrudUtil.executeQuery("Select * from Room where roomCategory = ? AND roomType = ? AND bedType = ? AND status = ?", roomCategory, roomType, bedType, available);
        ArrayList<Room> rooms = new ArrayList<>();
        while (rst.next()) {
            rooms.add(new Room(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getBigDecimal(6),
                    rst.getString(7)));
        }
        return rooms;
    }

    @Override
    public ArrayList<Room> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Room");
        ArrayList<Room> allRooms = new ArrayList<>();
        while (rst.next()) {
            allRooms.add(new Room(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getBigDecimal(6),
                    rst.getString(7)));
        }
        return allRooms;
    }

    @Override
    public ArrayList<String> getRoomCategory() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select distinct roomCategory from Room");
        ArrayList<String> roomCategory = new ArrayList<>();
        while (rst.next()) {
            roomCategory.add(rst.getString(1));
        }
        return roomCategory;
    }

    @Override
    public ArrayList<String> getRoomType() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select distinct roomType from Room");
        ArrayList<String> roomType = new ArrayList<>();
        while (rst.next()) {
            roomType.add(rst.getString(1));
        }
        return roomType;
    }

    @Override
    public ArrayList<String> getBedType() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select distinct bedType from Room");
        ArrayList<String> bedType = new ArrayList<>();
        while (rst.next()) {
            bedType.add(rst.getString(1));
        }
        return bedType;
    }
}
