package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.LogInBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.LogInDAO;
import lk.ijse.roombooking.entity.LogIn;
import lk.ijse.roombooking.model.LogInDTO;

import java.util.ArrayList;

public class LogInBOImpl implements LogInBO {

    private LogInDAO logInDAO;

    public LogInBOImpl() {
        logInDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LOGIN);
    }

    @Override
    public boolean addLogIn(LogInDTO logInDTO) throws Exception {
        return logInDAO.save(new LogIn(logInDTO.getUserName(), logInDTO.getLogInDate(), logInDTO.getLogInTime()));
    }

    @Override
    public LogInDTO searchUser(String userName) throws Exception {
        LogIn logIn = logInDAO.search(userName);
        if (logIn == null) {
            return null;
        } else {
            return new LogInDTO(logIn.getUserName(), logIn.getLogInDate(), logIn.getLogInTime());
        }
    }

    @Override
    public ArrayList<LogInDTO> getAllLogins() throws Exception {
        ArrayList<LogIn> logIns = logInDAO.getAll();
        ArrayList<LogInDTO> logInDTOS = new ArrayList<>();
        for (LogIn logIn : logIns) {
            logInDTOS.add(new LogInDTO(logIn.getUserName(), logIn.getLogInDate(), logIn.getLogInTime()));
        }
        return logInDTOS;
    }
}
