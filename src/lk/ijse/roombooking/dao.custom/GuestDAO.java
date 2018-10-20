package lk.ijse.roombooking.dao.custom;

import lk.ijse.roombooking.dao.CrudDAO;
import lk.ijse.roombooking.entity.Guest;

import java.util.ArrayList;

public interface GuestDAO extends CrudDAO<Guest, Integer> {
    Guest searchGuestByNic(String nic) throws Exception;

    Guest searchGuestByReservationId(String reservationId) throws Exception;

    ArrayList<Guest> getGuestsForReserved() throws Exception;
}
