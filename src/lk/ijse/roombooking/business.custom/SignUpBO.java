package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.SignUpDTO;

import java.util.ArrayList;

public interface SignUpBO extends SuperBO {
    boolean addUser(SignUpDTO signUpDTO) throws Exception;

    ArrayList<SignUpDTO> getAllUsers() throws Exception;

    SignUpDTO searchUser(String userName) throws Exception;

    boolean deleteUser(String userName) throws Exception;

    boolean updateUser(SignUpDTO signUpDTO) throws Exception;

    SignUpDTO getAdmin() throws Exception;
}
