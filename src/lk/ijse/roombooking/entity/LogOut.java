package lk.ijse.roombooking.entity;

import java.sql.Time;

public class LogOut {
    private String userName;
    private Time logOutTime;

    public LogOut() {
    }

    public LogOut(String userName, Time logOutTime) {
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
}
