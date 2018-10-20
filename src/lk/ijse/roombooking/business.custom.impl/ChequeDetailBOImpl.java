package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.ChequeDetailBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.ChequeDetailDAO;
import lk.ijse.roombooking.entity.ChequeDetail;
import lk.ijse.roombooking.model.ChequeDetailDTO;

import java.util.ArrayList;

public class ChequeDetailBOImpl implements ChequeDetailBO {
    private ChequeDetailDAO chequeDetailDAO;

    public ChequeDetailBOImpl() {
        chequeDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CHEQUE_DETAIL);
    }

    @Override
    public boolean addChequeDetail(ChequeDetailDTO chequeDetailDTO) throws Exception {
        return chequeDetailDAO.save(new ChequeDetail(chequeDetailDTO.getChequeDetailId(), chequeDetailDTO.getPaymentId(), chequeDetailDTO.getExpireDate(), chequeDetailDTO.getChequeNo(), chequeDetailDTO.getBank(), chequeDetailDTO.getBranch()));
    }

    @Override
    public ArrayList<ChequeDetailDTO> getAllChequeDetails() throws Exception {
        ArrayList<ChequeDetail> chequeDetails = chequeDetailDAO.getAll();
        ArrayList<ChequeDetailDTO> chequeDetailDTOArrayList = new ArrayList<>();
        for (ChequeDetail chequeDetail : chequeDetails) {
            chequeDetailDTOArrayList.add(new ChequeDetailDTO(chequeDetail.getChequeDetailId(), chequeDetail.getPaymentId(), chequeDetail.getExpireDate(), chequeDetail.getChequeNo(), chequeDetail.getBank(), chequeDetail.getBranch()));
        }
        return chequeDetailDTOArrayList;
    }
}
