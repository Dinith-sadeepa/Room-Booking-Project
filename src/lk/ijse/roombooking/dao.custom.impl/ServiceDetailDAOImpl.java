package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.ServiceDetailDAO;
import lk.ijse.roombooking.entity.ServiceDetail;

import java.util.ArrayList;

public class ServiceDetailDAOImpl implements ServiceDetailDAO {
    @Override
    public boolean save(ServiceDetail serviceDetail) throws Exception {
        return CrudUtil.executeUpdate("Insert into ServiceDetail values(?,?,?,?)", serviceDetail.getServiceDetailId(), serviceDetail.getServiceId(), serviceDetail.getReservationId(), serviceDetail.getPrice()) > 0;
    }

    @Override
    public boolean update(ServiceDetail entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ServiceDetail search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ServiceDetail> getAll() throws Exception {
        return null;
    }
}
