package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.ReservationDAO;
import lk.ijse.roombooking.entity.Reservation;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean save(Reservation reservation) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Reservation VALUES(?,?,?,?,?,?,?,?)", reservation.getReservationId(), reservation.getCheckInDate(), reservation.getCheckInTime(), reservation.getCheckOutDate(), reservation.getNoOfAdults(), reservation.getGuestId(), reservation.getReservationType(), reservation.getReservationFee()) > 0;
    }

    @Override
    public boolean update(Reservation entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer reservationId) throws Exception {
        return false;
    }

    @Override
    public Reservation search(Integer reservationId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from Reservation where reservationId = ?", reservationId);
        if (resultSet.next()) {
            return new Reservation(resultSet.getInt(1), resultSet.getDate(2), resultSet.getTime(3), resultSet.getDate(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getBigDecimal(8));
        }
        return null;
    }

    @Override
    public ArrayList<Reservation> getIdsForDeparting() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("select * from reservation where reservationId not in(select reservationId from departing);");
        ArrayList<Reservation> reservations = new ArrayList<>();
        while (resultSet.next()) {
            reservations.add(new Reservation(resultSet.getInt(1), resultSet.getDate(2), resultSet.getTime(3), resultSet.getDate(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getBigDecimal(8)));
        }
        return reservations;
    }

    @Override
    public ArrayList<Reservation> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from Reservation");
        ArrayList<Reservation> reservations = new ArrayList<>();
        while (resultSet.next()) {
            reservations.add(new Reservation(resultSet.getInt(1), resultSet.getDate(2), resultSet.getTime(3), resultSet.getDate(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getBigDecimal(8)));
        }
        return reservations;
    }

    @Override
    public Reservation searchReservationsForReserved(Integer guestId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select res.reservationId, checkInDate , checkInTime , checkOutDate , noOfAdults , res.guestId , reservationType , reservationFee from " +
                "Guest g , Reservation res , Reserved = r " +
                "where g.guestId = res.guestId AND res.reservationId = r.reservationId AND " +
                "res.reservationId not in(select reservationId from departing)");
        if (resultSet.next()) {
            return new Reservation(resultSet.getInt(1), resultSet.getDate(2), resultSet.getTime(3), resultSet.getDate(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getBigDecimal(8));
        }
        return null;
    }
}
