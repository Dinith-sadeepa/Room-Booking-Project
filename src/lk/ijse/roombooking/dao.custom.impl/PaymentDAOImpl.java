package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.PaymentDAO;
import lk.ijse.roombooking.entity.Payment;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment payment) throws Exception {
        return CrudUtil.executeUpdate("Insert into payment values(?,?,?,?,?)", payment.getReservationId(), payment.getPaymentId(), payment.getPayemntMethod(), payment.getAmount(), payment.getDate()) > 0;
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Payment search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Payment searchPaymentsByReservationId(Integer reservationId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Payment where reservationId = ?", reservationId);
        if (rst.next()) {
            return new Payment(rst.getInt(1), rst.getInt(2), rst.getString(3), rst.getBigDecimal(4), rst.getDate(5));
        }
        return null;
    }

    @Override
    public ArrayList<Payment> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from payment");
        ArrayList<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            payments.add(new Payment(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getBigDecimal(4), resultSet.getDate(5)));
        }
        return payments;
    }
}
