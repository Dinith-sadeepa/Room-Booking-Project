package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.PaymentBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.PaymentDAO;
import lk.ijse.roombooking.entity.Payment;
import lk.ijse.roombooking.model.PaymentDTO;

import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    private PaymentDAO paymentDAO;

    public PaymentBOImpl() {
        paymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        ArrayList<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOArrayList = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDTOArrayList.add(new PaymentDTO(payment.getReservationId(), payment.getPaymentId(), payment.getPayemntMethod(), payment.getAmount(), payment.getDate()));
        }
        return paymentDTOArrayList;
    }

    @Override
    public boolean addPayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.save(new Payment(paymentDTO.getReservationId(), paymentDTO.getPaymentId(), paymentDTO.getPayemntMethod(), paymentDTO.getAmount(), paymentDTO.getDate()));
    }

    @Override
    public ArrayList<Integer> getPaymentIds() throws Exception {
        ArrayList<Payment> payments = paymentDAO.getAll();
        ArrayList<Integer> paymentIds = new ArrayList<>();
        for (Payment payment : payments) {
            paymentIds.add(payment.getPaymentId());
        }
        return paymentIds;
    }

    @Override
    public PaymentDTO searchPaymentsByReservationId(Integer reservationId) throws Exception {
        Payment payment = paymentDAO.searchPaymentsByReservationId(reservationId);
        if (payment != null) {
            return new PaymentDTO(payment.getReservationId(), payment.getPaymentId(), payment.getPayemntMethod(), payment.getAmount(), payment.getDate());
        } else {
            return null;
        }
    }
}
