package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.LogInDTO;

import java.util.ArrayList;

public interface LogInBO extends SuperBO {
    boolean addLogIn(LogInDTO logInDTO) throws Exception;

    LogInDTO searchUser(String userName) throws Exception;

    ArrayList<LogInDTO> getAllLogins() throws Exception;
}
