package lk.ijse.roombooking.model;

import java.sql.Time;

public class LogOutDTO {
    private String userName;
    private Time logOutTime;

    public LogOutDTO() {
    }

    public LogOutDTO(String userName, Time logOutTime) {
        this.userName = userName;
        this.logOutTime = logOutTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Time getLogOutTime() {
        return logOutTime;
    }

    public void setLogOutTime(Time logOutTime) {
        this.logOutTime = logOutTime;
    }

    @Override
    public String toString() {
        return "LogOutDTO{" +
                "userName='" + userName + '\'' +
                ", logOutTime=" + logOutTime +
                '}';
    }
}
