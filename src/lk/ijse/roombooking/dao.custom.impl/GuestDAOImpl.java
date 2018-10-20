package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.GuestDAO;
import lk.ijse.roombooking.entity.Guest;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GuestDAOImpl implements GuestDAO {
    @Override
    public boolean save(Guest guest) throws Exception {
        return CrudUtil.executeUpdate("Insert Into Guest Values(?,?,?,?,?)", guest.getGuestId(), guest.getGender(), guest.getGuestName(), guest.getNic(), guest.getTelephoneNo()) > 0;
    }

    @Override
    public boolean update(Guest guest) throws Exception {
        return CrudUtil.executeUpdate("Update Guest SET gender = ? , guestName = ? , nic = ? , telephoneNo = ? where guestId = ?", guest.getGender(), guest.getGuestName(), guest.getNic(), guest.getTelephoneNo(), guest.getGuestId()) > 0;
    }

    @Override
    public boolean delete(Integer guestId) throws Exception {
        return CrudUtil.executeUpdate("Delete from Guest where guestId = ?", guestId) > 0;
    }

    @Override
    public Guest search(Integer guestId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Guest where guestId = ?", guestId);
        if (rst.next()) {
            return new Guest(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }


    @Override
    public Guest searchGuestByNic(String nic) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Guest where nic = ?", nic);
        if (rst.next()) {
            return new Guest(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }

    @Override
    public ArrayList<Guest> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Guest");
        ArrayList<Guest> guests = new ArrayList<>();
        while (rst.next()) {
            guests.add(new Guest(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
        }
        return guests;
    }

    @Override
    public Guest searchGuestByReservationId(String reservationId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Guest g , Reservation r where g.guestId = r.guestId AND reservationId = ?", reservationId);
        if (rst.next()) {
            return new Guest(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }

    @Override
    public ArrayList<Guest> getGuestsForReserved() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select g.guestId,gender,guestName,nic,telephoneNo from " +
                "Guest g , Reservation res , Reserved = r " +
                "where g.guestId = res.guestId AND res.reservationId = r.reservationId AND " +
                "res.reservationId not in(select reservationId from departing);");
        ArrayList<Guest> guestArrayList = new ArrayList<>();
        while (rst.next()) {
            guestArrayList.add(new Guest(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
        }
        return guestArrayList;
    }

}
