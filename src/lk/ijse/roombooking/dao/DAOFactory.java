package lk.ijse.roombooking.dao;

import lk.ijse.roombooking.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case ROOM:
                return (T) new RoomDAOImpl();
            case FACILITY:
                return (T) new FacilityDAOImpl();
            case SERVICE:
                return (T) new ServiceDAOImpl();
            case RESERVATION:
                return (T) new ReservationDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case GUEST:
                return (T) new GuestDAOImpl();
            case CHEQUE_DETAIL:
                return (T) new ChequeDetailDAOImpl();
            case SERVICE_DETAIL:
                return (T) new ServiceDetailDAOImpl();
            case FACILITY_DETAIL:
                return (T) new FacilityDetailDAOImpl();
            case ROOM_DETAIL:
                return (T) new RoomDetailDAOImpl();
            case DEPARTING:
                return (T) new DepartingDAOImpl();
            case RESERVED:
                return (T) new ReservedDAOImpl();
            case SIGNUP:
                return (T) new SignUpDAOImpl();
            case LOGIN:
                return (T) new LogInDAOImpl();
            case LOGOUT:
                return (T) new LogOutDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        ROOM, FACILITY, SERVICE, RESERVATION, PAYMENT, GUEST, SERVICE_DETAIL, FACILITY_DETAIL, CHEQUE_DETAIL, ROOM_DETAIL, DEPARTING, RESERVED, SIGNUP, LOGIN,
        LOGOUT;
    }
}
