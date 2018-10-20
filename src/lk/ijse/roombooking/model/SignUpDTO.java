package lk.ijse.roombooking.model;

import java.sql.Date;

public class SignUpDTO {
    private String userName;
    private String password;
    private String userType;
    private Date signUpDate;

    public SignUpDTO() {
    }

    public SignUpDTO(String userName, String password, String userType, Date signUpDate) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.signUpDate = signUpDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", signUpDate=" + signUpDate +
                '}';
    }
}
