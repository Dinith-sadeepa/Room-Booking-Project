package lk.ijse.roombooking.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ChequeDetail {
    private int chequeDetailId;
    private int paymentId;
    private Date expireDate;
    private String chequeNo;
    private String bank;
    private String branch;

    public ChequeDetail() {
    }

    public ChequeDetail(int paymentId, Date expireDate, String chequeNo, String bank, String branch) {
        this.paymentId = paymentId;
        this.expireDate = expireDate;
        this.chequeNo = chequeNo;
        this.bank = bank;
        this.branch = branch;
    }

    public ChequeDetail(int chequeDetailId, int paymentId, Date expireDate, String chequeNo, String bank, String branch) {
        this.chequeDetailId = chequeDetailId;
        this.paymentId = paymentId;
        this.expireDate = expireDate;
        this.chequeNo = chequeNo;
        this.bank = bank;
        this.branch = branch;
    }

    public int getChequeDetailId() {
        return chequeDetailId;
    }

    public void setChequeDetailId(int chequeDetailId) {
        this.chequeDetailId = chequeDetailId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
