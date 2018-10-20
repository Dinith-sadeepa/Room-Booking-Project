package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.FacilityDetailDAO;
import lk.ijse.roombooking.entity.FacilityDetail;

import java.util.ArrayList;

public class FacilityDetailDAOImpl implements FacilityDetailDAO {
    @Override
    public boolean save(FacilityDetail facilityDetail) throws Exception {
        return CrudUtil.executeUpdate("Insert into FacilityDetail values(?,?,?,?)", facilityDetail.getFacilityDetailId(), facilityDetail.getFacilityId(), facilityDetail.getReservationId(), facilityDetail.getPrice()) > 0;
    }

    @Override
    public boolean update(FacilityDetail entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public FacilityDetail search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<FacilityDetail> getAll() throws Exception {
        return null;
    }
}
