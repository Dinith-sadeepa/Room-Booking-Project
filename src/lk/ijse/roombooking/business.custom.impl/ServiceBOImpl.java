package lk.ijse.roombooking.business.custom.impl;

import lk.ijse.roombooking.business.custom.ServiceBO;
import lk.ijse.roombooking.dao.DAOFactory;
import lk.ijse.roombooking.dao.custom.ServiceDAO;
import lk.ijse.roombooking.entity.Service;
import lk.ijse.roombooking.model.ServiceDTO;

import java.util.ArrayList;

public class ServiceBOImpl implements ServiceBO {

    private ServiceDAO serviceDAO;

    public ServiceBOImpl() {
        serviceDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SERVICE);
    }

    @Override
    public boolean addService(ServiceDTO service) throws Exception {
        return serviceDAO.save(new Service(service.getServiceId(), service.getDescription(), service.getServicePrice()));
    }

    @Override
    public boolean deleteService(Integer serviceId) throws Exception {
        return serviceDAO.delete(serviceId);
    }

    @Override
    public boolean updateService(ServiceDTO service) throws Exception {
        return serviceDAO.update(new Service(service.getServiceId(), service.getDescription(), service.getServicePrice()));
    }

    @Override
    public ServiceDTO searchService(Integer serviceId) throws Exception {
        return null;
    }


    @Override
    public ServiceDTO searchServiceByDescription(String desc) throws Exception {
        Service service = serviceDAO.searchServiceByDescription(desc);
        return new ServiceDTO(service.getServiceId(), service.getDescription(), service.getServicePrice());
    }

    @Override
    public ArrayList<ServiceDTO> getAllService() throws Exception {
        ArrayList<Service> serviceList = serviceDAO.getAll();
        ArrayList<ServiceDTO> serviceDTOS = new ArrayList<>();
        for (Service service : serviceList) {
            serviceDTOS.add(new ServiceDTO(service.getServiceId(), service.getDescription(), service.getServicePrice()));
        }
        return serviceDTOS;
    }

    @Override
    public ArrayList<String> getAllServicesDescriptions() throws Exception {
        ArrayList<Service> allServices = serviceDAO.getAll();
        ArrayList<String> serviceDescription = new ArrayList<>();
        for (Service service : allServices) {
            serviceDescription.add(service.getDescription());
        }
        return serviceDescription;
    }

    @Override
    public ArrayList<ServiceDTO> searchServiceForReserved(Integer reservationId) throws Exception {
        ArrayList<Service> serviceList = serviceDAO.getServiceForReservedByReservationId(reservationId);
        ArrayList<ServiceDTO> serviceDTOS = new ArrayList<>();
        for (Service service : serviceList) {
            serviceDTOS.add(new ServiceDTO(service.getServiceId(), service.getDescription(), service.getServicePrice()));
        }
        return serviceDTOS;
    }
}
