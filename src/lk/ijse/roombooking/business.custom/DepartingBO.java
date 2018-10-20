package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.DepartingDTO;

import java.util.ArrayList;

public interface DepartingBO extends SuperBO {
    boolean addDeparting(DepartingDTO departingDTO) throws Exception;

    ArrayList<Integer> getDepartingIds() throws Exception;
}
