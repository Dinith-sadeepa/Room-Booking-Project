package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.PaymentDTO;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    ArrayList<PaymentDTO> getAllPayments() throws Exception;

    boolean addPayment(PaymentDTO paymentDTO) throws Exception;

    ArrayList<Integer> getPaymentIds() throws Exception;

    PaymentDTO searchPaymentsByReservationId(Integer reservationId) throws Exception;
}
