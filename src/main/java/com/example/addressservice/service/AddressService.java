package com.example.addressservice.service;

import com.example.addressservice.entity.Address;
import com.example.addressservice.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressService {

    private final AddressRepository repo;
    private final RestTemplate restTemplate;

    private static final String EMPLOYEE_URL =
            "http://localhost:8085/employees//by-employeeid/{employeeId}";

    private static final Logger logger =
            LoggerFactory.getLogger(AddressService.class);

    public AddressService(AddressRepository repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    public Address save(Address address) {
        logger.info("Saving address: {}", address);
        return repo.save(address);
    }

    public List<Address> getAll() {
        logger.info("Retrieving all addresses");
        return repo.findAll();
    }

    public Address getById(Integer id) {
        logger.info("Retrieving address with id: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public Address update(Integer id, Address address) {
        logger.info("Updating address with id: {}", id);
        Address existing = getById(id);
        existing.setAddress(address.getAddress());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        logger.info("Deleting address with id: {}", id);
        repo.deleteById(id);
    }
    public List<Address> getAllAddresses() {
        logger.info("Retrieving all addresses");
        return repo.findAll();
    }
    public List<Address> getAddressesByEmployeeId(Integer employeeId) {
        logger.info("Retrieving addresses for employeeId: {}", employeeId);
        return repo.findByEmployeeId(employeeId);
    }

    // ✅ FIXED METHOD
    public Map<String, Object> getEmployeeWithAddresses(Integer employeeId) {
        logger.info(" calling employee service for id :{}", employeeId);
        List<Object> employeeList= (List<Object>) restTemplate.getForObject(EMPLOYEE_URL, Object.class, employeeId);
        Object employee =null;
        if(employeeList!=null && !employeeList.isEmpty()){
            employee=employeeList.get(0);
        }

        logger.info("Employee response: {}", employee);
        List<Address> addresses =
                repo.findByEmployeeId(employeeId);

        Map<String, Object> response = new HashMap<>();
        response.put("employee", employee);
        response.put("addresses", addresses);

        return response;
    }
}
