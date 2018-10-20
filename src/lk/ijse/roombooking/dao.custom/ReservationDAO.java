package lk.ijse.roombooking.dao.custom;

import lk.ijse.roombooking.dao.CrudDAO;
import lk.ijse.roombooking.entity.Reservation;

import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation, Integer> {
    ArrayList<Reservation> getIdsForDeparting() throws Exception;

    Reservation searchReservationsForReserved(Integer guestId) throws Exception;
}
