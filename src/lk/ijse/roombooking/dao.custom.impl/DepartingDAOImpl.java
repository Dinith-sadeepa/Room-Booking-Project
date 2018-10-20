package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.DepartingDAO;
import lk.ijse.roombooking.entity.Departing;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DepartingDAOImpl implements DepartingDAO {
    @Override
    public boolean save(Departing departing) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Departing VALUES(?,?,?,?)", departing.getDepartingId(), departing.getReservationId(), departing.getCheckOutDate(), departing.getCheckOutTime()) > 0;
    }

    @Override
    public boolean update(Departing entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Departing search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Departing> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from departing");
        ArrayList<Departing> departings = new ArrayList<>();
        while (rst.next()) {
            departings.add(new Departing(rst.getInt(1), rst.getInt(2), rst.getDate(3), rst.getTime(4)));
        }
        return departings;
    }
}
