package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.RoomDetailDAO;
import lk.ijse.roombooking.entity.RoomDetail;

import java.util.ArrayList;

public class RoomDetailDAOImpl implements RoomDetailDAO {
    @Override
    public boolean save(RoomDetail roomDetail) throws Exception {
        return CrudUtil.executeUpdate("Insert into roomDetail values(?,?,?,?)", roomDetail.getRoomDetailId(), roomDetail.getRoomId(), roomDetail.getReservationId(), roomDetail.getPrice()) > 0;
    }

    @Override
    public boolean update(RoomDetail entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public RoomDetail search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<RoomDetail> getAll() throws Exception {
        return null;
    }
}
