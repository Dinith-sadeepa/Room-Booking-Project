package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.ServiceDAO;
import lk.ijse.roombooking.entity.Service;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public boolean save(Service service) throws Exception {
        return CrudUtil.executeUpdate("Insert Into Service Values(?,?,?)", service.getServiceId(), service.getDescription(), service.getServicePrice()) > 0;
    }

    @Override
    public boolean update(Service service) throws Exception {
        return CrudUtil.executeUpdate("Update Service set description = ? , servicePrice = ? where serviceId = ?", service.getDescription(), service.getServicePrice(), service.getServiceId()) > 0;
    }

    @Override
    public boolean delete(Integer serviceId) throws Exception {
        return CrudUtil.executeUpdate("Delete from service where serviceId = ?", serviceId) > 0;
    }

    @Override
    public Service search(Integer integer) throws Exception {
        return null;
    }


    @Override
    public Service searchServiceByDescription(String desc) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from Service where description = ?", desc);
        if (resultSet.next()) {
            return new Service(resultSet.getInt(1), resultSet.getString(2), resultSet.getBigDecimal(3));
        }
        return null;
    }

    @Override
    public ArrayList<Service> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Service");
        ArrayList<Service> services = new ArrayList<>();
        while (rst.next()) {
            services.add(new Service(rst.getInt(1),
                    rst.getString(2),
                    rst.getBigDecimal(3)));
        }
        return services;
    }

    @Override
    public ArrayList<Service> getServiceForReservedByReservationId(Integer reservationId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select s.serviceId , description , servicePrice  from service s , serviceDetail sd , reservation res where res.reservationId = sd.reservationId AND sd.serviceId = s.serviceId AND sd.reservationId = ?", reservationId);
        ArrayList<Service> services = new ArrayList<>();
        while (rst.next()) {
            services.add(new Service(rst.getInt(1),
                    rst.getString(2),
                    rst.getBigDecimal(3)));
        }
        return services;
    }
}
