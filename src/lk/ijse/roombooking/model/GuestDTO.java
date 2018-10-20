package lk.ijse.roombooking.model;

public class GuestDTO {
    private int guestId;
    private String gender;
    private String guestName;
    private String nic;
    private String telephoneNo;

    public GuestDTO() {
    }

    public GuestDTO(int guestId, String gender, String guestName, String nic, String telephoneNo) {
        this.guestId = guestId;
        this.gender = gender;
        this.guestName = guestName;
        this.nic = nic;
        this.telephoneNo = telephoneNo;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    @Override
    public String toString() {
        return "GuestDTO{" +
                "guestId=" + guestId +
                ", gender='" + gender + '\'' +
                ", guestName='" + guestName + '\'' +
                ", nic='" + nic + '\'' +
                ", telephoneNo='" + telephoneNo + '\'' +
                '}';
    }
}
