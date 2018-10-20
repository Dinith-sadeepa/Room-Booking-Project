package lk.ijse.roombooking.dao.custom;

import lk.ijse.roombooking.dao.CrudDAO;
import lk.ijse.roombooking.entity.Reserved;

public interface ReservedDAO extends CrudDAO<Reserved, Integer> {
    Reserved searchReservedByReservationId(Integer reservationId) throws Exception;
}
