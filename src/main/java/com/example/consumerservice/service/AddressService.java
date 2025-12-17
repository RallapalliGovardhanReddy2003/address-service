package com.example.consumerservice.service;
import com.example.consumerservice.entity.Address;
import com.example.consumerservice.repository.AddressRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repo;

    public AddressService(AddressRepository repo) {
        this.repo = repo;
    }

    public Address save(Address address) {
        return repo.save(address);
    }

    public List<Address> getAll() {
        return repo.findAll();
    }

    public Address getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public Address update(Integer id, Address adsress) {
        Address existing = getById(id);
        existing.setName(adsress.getName());
        existing.setAddress(adsress.getAddress());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

