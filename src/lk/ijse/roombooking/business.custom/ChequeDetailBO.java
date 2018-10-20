package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.ChequeDetailDTO;

import java.util.ArrayList;

public interface ChequeDetailBO extends SuperBO {
    boolean addChequeDetail(ChequeDetailDTO chequeDetailDTO) throws Exception;

    ArrayList<ChequeDetailDTO> getAllChequeDetails() throws Exception;
}
