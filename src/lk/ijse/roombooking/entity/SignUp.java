package lk.ijse.roombooking.entity;


import java.sql.Date;

public class SignUp {
    private String userName;
    private String password;
    private String userType;
    private Date signUpDate;

    public SignUp() {
    }

    public SignUp(String userName, String password, String userType, Date signUpDate) {
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
}
