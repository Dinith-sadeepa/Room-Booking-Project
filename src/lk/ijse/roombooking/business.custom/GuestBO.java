package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.GuestDTO;

import java.util.ArrayList;

public interface GuestBO extends SuperBO {
    public boolean addGuest(GuestDTO guestDTO) throws Exception;

    boolean updateGuest(GuestDTO guestDTO) throws Exception;

    boolean deleteGuest(Integer guestId) throws Exception;

    public ArrayList<GuestDTO> getAllGuests() throws Exception;

    ArrayList<String> getAllNics() throws Exception;

    GuestDTO searchGuestByNic(String nic) throws Exception;

    GuestDTO getGuestNic(String reservationId) throws Exception;

    ArrayList<Integer> getGuestIds() throws Exception;

    ArrayList<String> getGuestGender() throws Exception;

    ArrayList<GuestDTO> getAllGuestForReserved() throws Exception;
}
