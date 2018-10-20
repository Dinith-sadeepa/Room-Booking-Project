package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.ReservedBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.ReservedDAO;
import lk.ijse.roombooking.entity.Reserved;
import lk.ijse.roombooking.model.ReservedDTO;

import java.util.ArrayList;

public class ReservedBOImpl implements ReservedBO {
    private ReservedDAO reservedDAO;

    public ReservedBOImpl() {
        reservedDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVED);
    }

    @Override
    public ArrayList<ReservedDTO> getAllReserveds() throws Exception {
        ArrayList<Reserved> reservedArrayList = reservedDAO.getAll();
        ArrayList<ReservedDTO> reservedDTOArrayList = new ArrayList<>();
        for (Reserved reserved : reservedArrayList) {
            reservedDTOArrayList.add(new ReservedDTO(reserved.getReservedId(), reserved.getReservationId(), reserved.getReservedDate(), reserved.getReservedTime()));
        }
        return reservedDTOArrayList;
    }

    @Override
    public ReservedDTO searchReservedsByReservationId(Integer reservationId) throws Exception {
        Reserved reserved = reservedDAO.searchReservedByReservationId(reservationId);
        if (reserved != null) {
            return new ReservedDTO(reserved.getReservedId(), reserved.getReservationId(), reserved.getReservedDate(), reserved.getReservedTime());
        } else {
            return null;
        }
    }
}
