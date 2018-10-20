package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.ReservationDTO;

import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    public boolean addReservation(ReservationDTO reservationDTO) throws Exception;

    public ReservationDTO searchReservation(Integer reservationId) throws Exception;

    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception;

    public boolean deleteReservation(Integer reservationId) throws Exception;

    ArrayList<Integer> getReservationId() throws Exception;

    public ArrayList<ReservationDTO> getAllReservations() throws Exception;

    ReservationDTO searchReservationForReserved(Integer guestId) throws Exception;
}
