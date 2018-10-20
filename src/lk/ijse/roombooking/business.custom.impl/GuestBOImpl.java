package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.GuestBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.GuestDAO;
import lk.ijse.roombooking.entity.Guest;
import lk.ijse.roombooking.model.GuestDTO;

import java.util.ArrayList;

public class GuestBOImpl implements GuestBO {

    private GuestDAO guestDAO;

    public GuestBOImpl() {
        guestDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GUEST);
    }

    @Override
    public boolean addGuest(GuestDTO guestDTO) throws Exception {
        return guestDAO.save(new Guest(guestDTO.getGuestId(), guestDTO.getGender(), guestDTO.getGuestName(), guestDTO.getNic(), guestDTO.getTelephoneNo()));
    }

    @Override
    public boolean updateGuest(GuestDTO guestDTO) throws Exception {
        return guestDAO.update(new Guest(guestDTO.getGuestId(), guestDTO.getGender(), guestDTO.getGuestName(), guestDTO.getNic(), guestDTO.getTelephoneNo()));
    }

    @Override
    public boolean deleteGuest(Integer guestId) throws Exception {
        return guestDAO.delete(guestId);
    }

    @Override
    public ArrayList<GuestDTO> getAllGuests() throws Exception {
        ArrayList<Guest> guests = guestDAO.getAll();
        ArrayList<GuestDTO> guestDTOS = new ArrayList<>();
        for (Guest guest : guests) {
            guestDTOS.add(new GuestDTO(guest.getGuestId(), guest.getGender(), guest.getGuestName(), guest.getNic(), guest.getTelephoneNo()));
        }
        return guestDTOS;
    }

    @Override
    public ArrayList<String> getAllNics() throws Exception {
        ArrayList<Guest> guests = guestDAO.getAll();
        ArrayList<String> guestNics = new ArrayList<>();
        for (Guest guest : guests) {
            guestNics.add(guest.getNic());
        }
        return guestNics;
    }

    @Override
    public GuestDTO searchGuestByNic(String nic) throws Exception {
        Guest guest = guestDAO.searchGuestByNic(nic);
        if (guest == null) {
            return null;
        } else {
            return new GuestDTO(guest.getGuestId(), guest.getGender(), guest.getGuestName(), guest.getNic(), guest.getTelephoneNo());
        }
    }

    @Override
    public GuestDTO getGuestNic(String reservationId) throws Exception {
        Guest guest = guestDAO.searchGuestByReservationId(reservationId);
        return new GuestDTO(guest.getGuestId(), guest.getGender(), guest.getGuestName(), guest.getNic(), guest.getTelephoneNo());

    }

    @Override
    public ArrayList<Integer> getGuestIds() throws Exception {
        ArrayList<Guest> guests = guestDAO.getAll();
        ArrayList<Integer> guestsIds = new ArrayList<>();
        for (Guest guest : guests) {
            guestsIds.add(guest.getGuestId());
        }
        return guestsIds;
    }

    @Override
    public ArrayList<String> getGuestGender() throws Exception {
        ArrayList<Guest> guests = guestDAO.getAll();
        ArrayList<String> genders = new ArrayList<>();
        for (Guest guest : guests) {
            genders.add(guest.getGender());
        }
        return genders;
    }

    @Override
    public ArrayList<GuestDTO> getAllGuestForReserved() throws Exception {
        ArrayList<Guest> guestsForReserved = guestDAO.getGuestsForReserved();
        ArrayList<GuestDTO> guestDTOArrayList = new ArrayList<>();
        for (Guest guest : guestsForReserved) {
            guestDTOArrayList.add(new GuestDTO(guest.getGuestId(), guest.getGender(), guest.getGuestName(), guest.getNic(), guest.getTelephoneNo()));
        }
        return guestDTOArrayList;
    }
}
