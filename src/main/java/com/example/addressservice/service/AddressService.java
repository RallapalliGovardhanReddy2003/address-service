package com.example.addressservice.service;

import com.example.addressservice.client.EmployeeClient;
import com.example.addressservice.entity.Address;
import com.example.addressservice.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressService {

    private static final  Logger logger = LoggerFactory.getLogger(AddressService.class);
    @Autowired
    private final AddressRepository repo;
    @Autowired
    private EmployeeClient employeeClient;


    public AddressService(AddressRepository repo) {
        this.repo = repo;
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
    public Map<String, Object> getEmployeeWithAddress(Integer employeeId) {
        logger.info("calling employee service id :{}", employeeId);

        Map<String, Object> employee = employeeClient.getEmployeeById(employeeId);
        logger.info("Employee response from Employee-service :{}", employee);

        List<Address> address = repo.findByEmployeeId(employeeId);

        logger.info("Address response:{}", address);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("employee", employee);
        response.put("Address", address);


        logger.info("Final response:{}", response);


        return response;


    }
    public List<Address> getByEmployeeId(Integer employeeId) {
        return repo.findByEmployeeId(employeeId);
    }



}
