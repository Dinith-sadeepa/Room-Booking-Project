package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.FacilityBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.FacilityDAO;
import lk.ijse.roombooking.entity.Facility;
import lk.ijse.roombooking.model.FacilityDTO;

import java.util.ArrayList;

public class FacilityBOImpl implements FacilityBO {
    private FacilityDAO facilityDAO;

    public FacilityBOImpl() {
        facilityDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.FACILITY);
    }

    @Override
    public boolean addFacility(FacilityDTO facilityDTO) throws Exception {
        return facilityDAO.save(new Facility(facilityDTO.getFacilityId(), facilityDTO.getDescription(), facilityDTO.getFacilityPrice()));
    }

    @Override
    public FacilityDTO searchFacility(Integer fId) throws Exception {
        Facility facility = facilityDAO.search(fId);
        return new FacilityDTO(facility.getFacilityId(), facility.getDescription(), facility.getFacilityPrice());
    }

    @Override
    public FacilityDTO searchFacilityByDescription(String description) throws Exception {
        Facility facility = facilityDAO.searchByDescription(description);
        return new FacilityDTO(facility.getFacilityId(), facility.getDescription(), facility.getFacilityPrice());
    }

    @Override
    public boolean updateFacility(FacilityDTO facilityDTO) throws Exception {
        return facilityDAO.update(new Facility(facilityDTO.getFacilityId(), facilityDTO.getDescription(), facilityDTO.getFacilityPrice()));
    }

    @Override
    public boolean deleteFacility(Integer fId) throws Exception {
        return facilityDAO.delete(fId);
    }


    @Override
    public ArrayList<FacilityDTO> getAllFacilities() throws Exception {
        ArrayList<Facility> facilities = facilityDAO.getAll();
        ArrayList<FacilityDTO> facilityDTOS = new ArrayList<>();
        for (Facility facility : facilities) {
            facilityDTOS.add(new FacilityDTO(facility.getFacilityId(), facility.getDescription(), facility.getFacilityPrice()));
        }
        return facilityDTOS;
    }

    @Override
    public ArrayList<String> getAllFacilitiesDescriptions() throws Exception {
        ArrayList<Facility> allFacilities = facilityDAO.getAll();
        ArrayList<String> facilityDescriptions = new ArrayList<>();
        for (Facility facility : allFacilities) {
            facilityDescriptions.add(facility.getDescription());
        }
        return facilityDescriptions;
    }

    @Override
    public ArrayList<FacilityDTO> searchFacilityForReserved(Integer reservationId) throws Exception {
        ArrayList<Facility> facilities = facilityDAO.getFacilityForReservedByReservationId(reservationId);
        ArrayList<FacilityDTO> facilityDTOS = new ArrayList<>();
        for (Facility facility : facilities) {
            facilityDTOS.add(new FacilityDTO(facility.getFacilityId(), facility.getDescription(), facility.getFacilityPrice()));
        }
        return facilityDTOS;
    }
}
