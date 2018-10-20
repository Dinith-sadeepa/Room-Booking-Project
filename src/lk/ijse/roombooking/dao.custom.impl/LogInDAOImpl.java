package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.LogInDAO;
import lk.ijse.roombooking.entity.LogIn;

import java.sql.ResultSet;
import java.util.ArrayList;

public class LogInDAOImpl implements LogInDAO {
    @Override
    public boolean save(LogIn logIn) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO LogIn VALUES(?,?,?)", logIn.getUserName(), logIn.getLogInDate(), logIn.getLogInTime()) > 0;
    }

    @Override
    public boolean update(LogIn entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public LogIn search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<LogIn> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from LogIn");
        ArrayList<LogIn> logIns = new ArrayList<>();
        while (resultSet.next()) {
            logIns.add(new LogIn(resultSet.getString(1), resultSet.getDate(2), resultSet.getTime(3)));
        }
        return logIns;
    }
}
