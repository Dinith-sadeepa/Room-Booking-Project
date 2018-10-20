package lk.ijse.roombooking.dao.custom;

import lk.ijse.roombooking.dao.CrudDAO;
import lk.ijse.roombooking.entity.Facility;

import java.util.ArrayList;

public interface FacilityDAO extends CrudDAO<Facility, Integer> {
    Facility search(int fId) throws Exception;

    public Facility searchByDescription(String description) throws Exception;

    ArrayList<Facility> getFacilityForReservedByReservationId(Integer reservationId) throws Exception;
}
