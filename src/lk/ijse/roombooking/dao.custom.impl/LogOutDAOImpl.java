package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.LogOutDAO;
import lk.ijse.roombooking.entity.LogOut;

import java.util.ArrayList;

public class LogOutDAOImpl implements LogOutDAO {
    @Override
    public boolean save(LogOut logOut) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO LogOut VALUES(?,?)", logOut.getUserName(), logOut.getLogOutTime()) > 0;
    }

    @Override
    public boolean update(LogOut entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public LogOut search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<LogOut> getAll() throws Exception {
        return null;
    }
}
