package lk.ijse.roombooking.model;

import java.sql.Date;
import java.sql.Time;

public class LogInDTO {
    private String userName;
    private Date logInDate;
    private Time logInTime;

    public LogInDTO() {
    }

    public LogInDTO(String userName, Date logInDate, Time logInTime) {
        this.userName = userName;
        this.logInDate = logInDate;
        this.logInTime = logInTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLogInDate() {
        return logInDate;
    }

    public void setLogInDate(Date logInDate) {
        this.logInDate = logInDate;
    }

    public Time getLogInTime() {
        return logInTime;
    }

    public void setLogInTime(Time logInTime) {
        this.logInTime = logInTime;
    }

    @Override
    public String toString() {
        return "LogInDTO{" +
                "userName='" + userName + '\'' +
                ", logInDate=" + logInDate +
                ", logInTime=" + logInTime +
                '}';
    }
}
