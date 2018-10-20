package lk.ijse.roombooking.dao.custom;

import lk.ijse.roombooking.dao.CrudDAO;
import lk.ijse.roombooking.entity.Payment;

public interface PaymentDAO extends CrudDAO<Payment, Integer> {
    Payment searchPaymentsByReservationId(Integer reservationId) throws Exception;
}
