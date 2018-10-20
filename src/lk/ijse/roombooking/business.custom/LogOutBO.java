package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.LogOutDTO;

public interface LogOutBO extends SuperBO {
    boolean addLogOut(LogOutDTO logOutDTO) throws Exception;
}
