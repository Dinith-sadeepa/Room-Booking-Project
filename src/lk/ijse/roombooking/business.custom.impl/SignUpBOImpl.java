package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.SignUpBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.SignUpDAO;
import lk.ijse.roombooking.entity.SignUp;
import lk.ijse.roombooking.model.SignUpDTO;

import java.util.ArrayList;

public class SignUpBOImpl implements SignUpBO {
    private SignUpDAO signUpDAO;

    public SignUpBOImpl() {
        signUpDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SIGNUP);
    }

    @Override
    public boolean addUser(SignUpDTO signUpDTO) throws Exception {
        return signUpDAO.save(new SignUp(signUpDTO.getUserName(), signUpDTO.getPassword(), signUpDTO.getUserType(), signUpDTO.getSignUpDate()));
    }

    @Override
    public ArrayList<SignUpDTO> getAllUsers() throws Exception {
        ArrayList<SignUp> userEntities = signUpDAO.getAll();
        ArrayList<SignUpDTO> signUpDTOS = new ArrayList<>();
        for (SignUp signUp : userEntities) {
            signUpDTOS.add(new SignUpDTO(signUp.getUserName(), signUp.getPassword(), signUp.getUserType(), signUp.getSignUpDate()));
        }
        return signUpDTOS;
    }

    @Override
    public SignUpDTO searchUser(String userName) throws Exception {
        SignUp signUp = signUpDAO.search(userName);
        if (signUp == null) {
            return null;
        } else {
            return new SignUpDTO(signUp.getUserName(), signUp.getPassword(), signUp.getUserType(), signUp.getSignUpDate());
        }
    }

    @Override
    public boolean deleteUser(String userName) throws Exception {
        return signUpDAO.delete(userName);
    }

    @Override
    public boolean updateUser(SignUpDTO signUpDTO) throws Exception {
        return signUpDAO.update(new SignUp(signUpDTO.getUserName(), signUpDTO.getPassword(), signUpDTO.getUserType(), signUpDTO.getSignUpDate()));
    }

    @Override
    public SignUpDTO getAdmin() throws Exception {
        ArrayList<SignUp> all = signUpDAO.getAll();
        for (SignUp signUp : all) {
            if (signUp.getUserType().equals("admin")) {
                return new SignUpDTO(signUp.getUserName(), signUp.getPassword(), signUp.getUserType(), signUp.getSignUpDate());
            }
        }
        return null;
    }
}
