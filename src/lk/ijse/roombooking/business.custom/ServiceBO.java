package lk.ijse.roombooking.business.custom;

import lk.ijse.roombooking.business.SuperBO;
import lk.ijse.roombooking.model.RoomDTO;
import lk.ijse.roombooking.model.ServiceDTO;

import java.util.ArrayList;

public interface ServiceBO extends SuperBO {
    public boolean addService(ServiceDTO service) throws Exception;

    public boolean deleteService(Integer serviceId) throws Exception;

    public boolean updateService(ServiceDTO service) throws Exception;

    public ServiceDTO searchService(Integer serviceId) throws Exception;

    ServiceDTO searchServiceByDescription(String desc) throws Exception;

    public ArrayList<ServiceDTO> getAllService() throws Exception;

    public ArrayList<String> getAllServicesDescriptions() throws Exception;

    ArrayList<ServiceDTO> searchServiceForReserved(Integer reservationId) throws Exception;
}
