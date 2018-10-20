package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.ReservedDAO;
import lk.ijse.roombooking.entity.Reserved;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservedDAOImpl implements ReservedDAO {
    @Override
    public boolean save(Reserved reserved) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Reserved VALUES(?,?,?,?)", reserved.getReservedId(), reserved.getReservationId(), reserved.getReservedDate(), reserved.getReservedTime()) > 0;
    }

    @Override
    public boolean update(Reserved entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Reserved search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Reserved> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from Reserved");
        ArrayList<Reserved> reservedArrayList = new ArrayList<>();
        while (resultSet.next()) {
            reservedArrayList.add(new Reserved(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getTime(4)));
        }
        return reservedArrayList;
    }

    @Override
    public Reserved searchReservedByReservationId(Integer reservationId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from Reserved where reservationId = ?", reservationId);
        if (resultSet.next()) {
            return new Reserved(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getTime(4));
        } else {
            return null;
        }
    }
}
