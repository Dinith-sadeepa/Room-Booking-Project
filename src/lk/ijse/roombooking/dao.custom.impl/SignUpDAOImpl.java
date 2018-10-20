package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.SignUpDAO;
import lk.ijse.roombooking.entity.SignUp;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SignUpDAOImpl implements SignUpDAO {
    @Override
    public boolean save(SignUp signUp) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO SignUp VALUES(?,?,?,?)", signUp.getUserName(), signUp.getPassword(), signUp.getUserType(), signUp.getSignUpDate()) > 0;
    }

    @Override
    public boolean update(SignUp signUp) throws Exception {
        return CrudUtil.executeUpdate("Update SignUp set password = ? , userType = ? , signUpDate = ? where userName = ?", signUp.getPassword(), signUp.getUserType(), signUp.getSignUpDate(), signUp.getUserName()) > 0;
    }

    @Override
    public boolean delete(String userName) throws Exception {
        return CrudUtil.executeUpdate("Delete from SignUp where userName = ?", userName) > 0;
    }

    @Override
    public SignUp search(String userName) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from SignUp where userName = ?", userName);
        if (resultSet.next()) {
            return new SignUp(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<SignUp> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from SignUp");
        ArrayList<SignUp> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new SignUp(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4)));
        }
        return users;
    }
}
