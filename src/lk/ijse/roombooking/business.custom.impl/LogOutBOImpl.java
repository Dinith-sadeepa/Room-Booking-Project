package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.LogOutBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.LogOutDAO;
import lk.ijse.roombooking.entity.LogOut;
import lk.ijse.roombooking.model.LogOutDTO;

public class LogOutBOImpl implements LogOutBO {

    private LogOutDAO logOutDAO;

    public LogOutBOImpl() {
        logOutDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LOGOUT);
    }

    @Override
    public boolean addLogOut(LogOutDTO logOutDTO) throws Exception {
        return logOutDAO.save(new LogOut(logOutDTO.getUserName(), logOutDTO.getLogOutTime()));
    }
}
