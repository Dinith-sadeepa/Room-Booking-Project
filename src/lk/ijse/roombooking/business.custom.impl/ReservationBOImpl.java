package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.ReservationBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.*;
import lk.ijse.roombooking.db.DBConnection;
import lk.ijse.roombooking.entity.*;
import lk.ijse.roombooking.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    private ReservationDAO reservationDAO;
    private ReservedDAO reservedDAO;
    private PaymentDAO paymentDAO;
    private ServiceDetailDAO serviceDetailDAO;
    private FacilityDetailDAO facilityDetailDAO;
    private ChequeDetailDAO chequeDetailDAO;
    private GuestDAO guestDAO;
    private RoomDetailDAO roomDetailDAO;
    private RoomDAO roomDAO;


    public ReservationBOImpl() {
        reservationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVATION);
        reservedDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVED);
        paymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
        serviceDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SERVICE_DETAIL);
        facilityDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.FACILITY_DETAIL);
        chequeDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CHEQUE_DETAIL);
        guestDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GUEST);
        roomDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM_DETAIL);
        roomDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
    }

    @Override
    public boolean addReservation(ReservationDTO reservationDTO) throws Exception {
        System.out.println(reservationDTO.getFacilityDetailList().isEmpty());
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isAddedGuest;
            Guest searchedGuest = guestDAO.search(reservationDTO.getGuestDTO().getGuestId());
            if (searchedGuest != null) {
                isAddedGuest = true;
            } else {
                isAddedGuest = guestDAO.save(new Guest(reservationDTO.getGuestDTO().getGuestId(), reservationDTO.getGuestDTO().getGender(), reservationDTO.getGuestDTO().getGuestName(), reservationDTO.getGuestDTO().getNic(), reservationDTO.getGuestDTO().getTelephoneNo()));
            }
            if (isAddedGuest) {
                boolean isAddedReservation = reservationDAO.save(new Reservation(reservationDTO.getReservationId(), reservationDTO.getCheckInDate(), reservationDTO.getCheckInTime(), reservationDTO.getCheckOutDate(), reservationDTO.getNoOfAdults(), reservationDTO.getGuestId(), reservationDTO.getReservationType(), reservationDTO.getReservationFee()));
                System.out.println("a");
                if (isAddedReservation) {
                    boolean isAddedFacilityDetail = false;
                    if (reservationDTO.getFacilityDetailList().isEmpty()) {
                        isAddedFacilityDetail = true;
                    } else {
                        for (FacilityDetailDTO facilityDetailDTO : reservationDTO.getFacilityDetailList()) {
                            isAddedFacilityDetail = facilityDetailDAO.save(new FacilityDetail(facilityDetailDTO.getFacilityId(), facilityDetailDTO.getReservationId(), facilityDetailDTO.getPrice()));
                        }
                    }
                    if (isAddedFacilityDetail) {
                        boolean isAddedServiceDetail = false;
                        if (reservationDTO.getServiceDetailList().isEmpty()) {
                            isAddedServiceDetail = true;
                        } else {
                            for (ServiceDetailDTO serviceDetailDTO : reservationDTO.getServiceDetailList()) {
                                isAddedServiceDetail = serviceDetailDAO.save(new ServiceDetail(serviceDetailDTO.getServiceId(), serviceDetailDTO.getReservationId(), serviceDetailDTO.getPrice()));
                            }
                        }
                        if (isAddedServiceDetail) {
                            boolean isAddedPayment = false;
                            boolean isReserved = false;
                            if (reservationDTO.getReservationType().equals("on payment")) {
                                for (PaymentDTO paymentDTO : reservationDTO.getPaymentList()) {
                                    if (paymentDTO.getPayemntMethod().equals("cash")) {
                                        isAddedPayment = paymentDAO.save(new Payment(paymentDTO.getReservationId(), paymentDTO.getPaymentId(), paymentDTO.getPayemntMethod(), paymentDTO.getAmount(), paymentDTO.getDate()));
                                    } else {
                                        for (ChequeDetailDTO chequeDetailDTO : reservationDTO.getChequeDetailList()) {
                                            isAddedPayment = paymentDAO.save(new Payment(paymentDTO.getReservationId(), paymentDTO.getPaymentId(), paymentDTO.getPayemntMethod(), paymentDTO.getAmount(), paymentDTO.getDate()));
                                            if (isAddedPayment) {
                                                chequeDetailDAO.save(new ChequeDetail(chequeDetailDTO.getChequeDetailId(), chequeDetailDTO.getPaymentId(), chequeDetailDTO.getExpireDate(), chequeDetailDTO.getChequeNo(), chequeDetailDTO.getBank(), chequeDetailDTO.getBranch()));
                                            }
                                        }
                                    }
                                }
                            } else {
                                for (ReservedDTO reservedDTO : reservationDTO.getReservedDetailList()) {
                                    isReserved = reservedDAO.save(new Reserved(reservedDTO.getReservedId(), reservedDTO.getReservationId(), (Date) reservedDTO.getReservedDate(), reservedDTO.getReservedTime()));
                                }
                            }
                            if (isAddedPayment || isReserved) {
                                boolean isUpdateRoom = false;
                                for (RoomDetailDTO roomDetailDTO : reservationDTO.getRoomDetailList()) {
                                    boolean isAddedRoomDetail = roomDetailDAO.save(new RoomDetail(roomDetailDTO.getRoomId(), roomDetailDTO.getReservationId(), roomDetailDTO.getRoomPrice()));
                                    if (isAddedRoomDetail) {
                                        Room room = roomDAO.search(roomDetailDTO.getRoomId());
                                        String status = "Booked";
                                        room.setStatus(status);
                                        isUpdateRoom = roomDAO.update(room);
                                    }
                                }
                                if (isUpdateRoom) {
                                    connection.commit();
                                    return true;
                                }
                            }
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
    public ReservationDTO searchReservation(Integer reservationId) throws Exception {
        Reservation reservation = reservationDAO.search(reservationId);
        return new ReservationDTO(reservation.getReservationId(), reservation.getCheckInDate(), reservation.getCheckInTime(), reservation.getCheckOutDate(), reservation.getNoOfAdults(), reservation.getGuestId(), reservation.getReservationType(), reservation.getReservationFee());
    }


    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteReservation(Integer reservationId) throws Exception {
        return false;
    }


    @Override
    public ArrayList<Integer> getReservationId() throws Exception {
        ArrayList<Reservation> reservations = reservationDAO.getIdsForDeparting();
        ArrayList<Integer> reservationIds = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationIds.add(reservation.getReservationId());
        }
        return reservationIds;
    }

    @Override
    public ArrayList<ReservationDTO> getAllReservations() throws Exception {
        ArrayList<Reservation> reservationsList = reservationDAO.getAll();
        ArrayList<ReservationDTO> reservationDTOArrayList = new ArrayList<>();
        for (Reservation reservation : reservationsList) {
            reservationDTOArrayList.add(new ReservationDTO(reservation.getReservationId(), reservation.getCheckInDate(), reservation.getCheckInTime(), reservation.getCheckOutDate(), reservation.getNoOfAdults(), reservation.getGuestId(), reservation.getReservationType(), reservation.getReservationFee()));
        }
        return reservationDTOArrayList;
    }

    @Override
    public ReservationDTO searchReservationForReserved(Integer guestId) throws Exception {
        Reservation reservation = reservationDAO.searchReservationsForReserved(guestId);
        if (reservation != null) {
            return new ReservationDTO(reservation.getReservationId(), reservation.getCheckInDate(), reservation.getCheckInTime(), reservation.getCheckOutDate(), reservation.getNoOfAdults(), reservation.getGuestId(), reservation.getReservationType(), reservation.getReservationFee());
        } else {
            return null;
        }
    }
}
