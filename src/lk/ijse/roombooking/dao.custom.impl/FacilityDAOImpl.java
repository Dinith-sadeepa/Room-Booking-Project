package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.FacilityDAO;
import lk.ijse.roombooking.entity.Facility;

import java.sql.ResultSet;
import java.util.ArrayList;

public class FacilityDAOImpl implements FacilityDAO {
    @Override
    public boolean save(Facility facility) throws Exception {
        return CrudUtil.executeUpdate("Insert into facility values(?,?,?)", facility.getFacilityId(), facility.getDescription(), facility.getFacilityPrice()) > 0;
    }

    @Override
    public boolean update(Facility facility) throws Exception {
        return CrudUtil.executeUpdate("Update Facility set description = ? , facilityPrice = ? where facilityId = ?", facility.getDescription(), facility.getFacilityPrice(), facility.getFacilityId()) > 0;
    }

    @Override
    public boolean delete(Integer facilityId) throws Exception {
        return CrudUtil.executeUpdate("Delete from Facility where facilityId = ?", facilityId) > 0;
    }

    @Override
    public Facility search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Facility search(int fId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from facility where fId = ?", fId);
        if (rst.next()) {
            return new Facility(rst.getInt(1), rst.getString(2), rst.getBigDecimal(3));
        } else {
            return null;
        }
    }

    @Override
    public Facility searchByDescription(String desc) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from facility where description=?", desc);
        if (rst.next()) {
            return new Facility(rst.getInt(1), rst.getString(2), rst.getBigDecimal(3));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Facility> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Facility");
        ArrayList<Facility> facilities = new ArrayList<>();
        while (rst.next()) {
            facilities.add(new Facility(rst.getInt(1),
                    rst.getString(2),
                    rst.getBigDecimal(3)));
        }
        return facilities;
    }

    @Override
    public ArrayList<Facility> getFacilityForReservedByReservationId(Integer reservationId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select f.facilityId , description , facilityPrice  from facility f , facilityDetail fd , reservation res where res.reservationId = fd.reservationId AND fd.facilityId = f.facilityId AND fd.reservationId = ?", reservationId);
        ArrayList<Facility> facilities = new ArrayList<>();
        while (rst.next()) {
            facilities.add(new Facility(rst.getInt(1),
                    rst.getString(2),
                    rst.getBigDecimal(3)));
        }
        return facilities;
    }
}
