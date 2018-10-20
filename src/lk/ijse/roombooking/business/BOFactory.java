package lk.ijse.roombooking.business;

import lk.ijse.roombooking.business.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case ROOM:
                return (T) new RoomBOImpl();
            case FACILITY:
                return (T) new FacilityBOImpl();
            case SERVICE:
                return (T) new ServiceBOImpl();
            case RESERVATION:
                return (T) new ReservationBOImpl();
            case GUEST:
                return (T) new GuestBOImpl();
            case PAYMENT:
                return (T) new PaymentBOImpl();
            case CHEQUEDETAIL:
                return (T) new ChequeDetailBOImpl();
            case DEPARTING:
                return (T) new DepartingBOImpl();
            case RESERVED:
                return (T) new ReservedBOImpl();
            case SIGNUP:
                return (T) new SignUpBOImpl();
            case LOGIN:
                return (T) new LogInBOImpl();
            case LOGOUT:
                return (T) new LogOutBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        ROOM, FACILITY, SERVICE, RESERVATION, GUEST, PAYMENT, CHEQUEDETAIL, DEPARTING, RESERVED, SIGNUP, LOGIN, LOGOUT;
    }
}
