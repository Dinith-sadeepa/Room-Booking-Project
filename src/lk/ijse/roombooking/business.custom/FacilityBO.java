package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.FacilityDTO;

import java.util.ArrayList;

public interface FacilityBO extends SuperBO {
    public boolean addFacility(FacilityDTO facilityDTO) throws Exception;

    public FacilityDTO searchFacility(Integer fId) throws Exception;

    public FacilityDTO searchFacilityByDescription(String description) throws Exception;

    public boolean updateFacility(FacilityDTO facilityDTO) throws Exception;

    public boolean deleteFacility(Integer fId) throws Exception;

    public ArrayList<FacilityDTO> getAllFacilities() throws Exception;

    public ArrayList<String> getAllFacilitiesDescriptions() throws Exception;

    ArrayList<FacilityDTO> searchFacilityForReserved(Integer reservationId) throws Exception;
}
