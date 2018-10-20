package lk.ijse.roombooking.dao.custom;

import lk.ijse.roombooking.dao.CrudDAO;
import lk.ijse.roombooking.entity.Service;

import java.util.ArrayList;

public interface ServiceDAO extends CrudDAO<Service, Integer> {
    Service searchServiceByDescription(String desc) throws Exception;

    ArrayList<Service> getServiceForReservedByReservationId(Integer reservationId) throws Exception;
}
