package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.ReservedDTO;

import java.util.ArrayList;

public interface ReservedBO extends SuperBO {
    ArrayList<ReservedDTO> getAllReserveds() throws Exception;

    ReservedDTO searchReservedsByReservationId(Integer reservationId) throws Exception;
}
