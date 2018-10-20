package lk.ijse.roombooking.dao.custom.impl;

import lk.ijse.roombooking.dao.CrudUtil;
import lk.ijse.roombooking.dao.custom.ChequeDetailDAO;
import lk.ijse.roombooking.entity.ChequeDetail;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ChequeDetailDAOImpl implements ChequeDetailDAO {
    @Override
    public boolean save(ChequeDetail chequeDetail) throws Exception {
        return CrudUtil.executeUpdate("Insert Into ChequeDetail values(?,?,?,?,?,?)", chequeDetail.getChequeDetailId(), chequeDetail.getPaymentId(), chequeDetail.getExpireDate(), chequeDetail.getChequeNo(), chequeDetail.getBank(), chequeDetail.getBranch()) > 0;
    }

    @Override
    public boolean update(ChequeDetail entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ChequeDetail search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ChequeDetail> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("Select * from ChequeDetail");
        ArrayList<ChequeDetail> chequeDetails = new ArrayList<>();
        while (resultSet.next()) {
            chequeDetails.add(new ChequeDetail(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return chequeDetails;
    }
}
