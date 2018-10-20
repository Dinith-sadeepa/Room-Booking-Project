package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.DepartingBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.ChequeDetailDAO;
import lk.ijse.roombooking.dao.custom.DepartingDAO;
import lk.ijse.roombooking.dao.custom.PaymentDAO;
import lk.ijse.roombooking.dao.custom.RoomDAO;
import lk.ijse.roombooking.db.DBConnection;
import lk.ijse.roombooking.entity.ChequeDetail;
import lk.ijse.roombooking.entity.Departing;
import lk.ijse.roombooking.entity.Payment;
import lk.ijse.roombooking.entity.Room;
import lk.ijse.roombooking.model.ChequeDetailDTO;
import lk.ijse.roombooking.model.DepartingDTO;
import lk.ijse.roombooking.model.PaymentDTO;

import java.sql.Connection;
import java.util.ArrayList;

public class DepartingBOImpl implements DepartingBO {
    private DepartingDAO departingDAO;
    private PaymentDAO paymentDAO;
    private RoomDAO roomDAO;
    private ChequeDetailDAO chequeDetailDAO;

    public DepartingBOImpl() {
        departingDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DEPARTING);
        paymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
        roomDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
        chequeDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CHEQUE_DETAIL);
    }

    @Override
    public boolean addDeparting(DepartingDTO departingDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isAddedDeparting = departingDAO.save(new Departing(departingDTO.getDepartingId(), departingDTO.getReservationId(), departingDTO.getCheckOutDate(), departingDTO.getCheckOutTime()));
            if (isAddedDeparting) {
                boolean isAddedPayment = false;
                for (PaymentDTO paymentDTO : departingDTO.getPaymentList()) {
                    isAddedPayment = paymentDAO.save(new Payment(paymentDTO.getReservationId(), paymentDTO.getPaymentId(), paymentDTO.getPayemntMethod(), paymentDTO.getAmount(), paymentDTO.getDate()));
                    if (isAddedPayment) {
                        if (paymentDTO.getPayemntMethod().equals("cheque")) {
                            for (ChequeDetailDTO chequeDetailDTO : departingDTO.getChequeDetailList()) {
                                isAddedPayment = chequeDetailDAO.save(new ChequeDetail(chequeDetailDTO.getChequeDetailId(), chequeDetailDTO.getPaymentId(), chequeDetailDTO.getExpireDate(), chequeDetailDTO.getChequeNo(), chequeDetailDTO.getBank(), chequeDetailDTO.getBranch()));
                            }
                        }
                    }
                    if (isAddedPayment) {
                        boolean isUpdateRoom = false;
                        ArrayList<Room> reservedRoomByReservationId = roomDAO.getReservedRoomByReservationId(departingDTO.getReservationId());
                        for (Room room : reservedRoomByReservationId) {
                            room.setStatus("Available");
                            isUpdateRoom = roomDAO.update(room);
                        }
                        if (isUpdateRoom) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            return false;
        } finally {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ArrayList<Integer> getDepartingIds() throws Exception {
        ArrayList<Departing> departings = departingDAO.getAll();
        ArrayList<Integer> departingIds = new ArrayList<>();
        for (Departing departing : departings) {
            departingIds.add(departing.getDepartingId());
        }
        return departingIds;
    }
}
