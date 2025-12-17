package com.example.consumerservice.controller;
import com.example.consumerservice.entity.Address;
import com.example.consumerservice.service.AddressService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Address> create(@Validated @RequestBody Address address) {
        Address adsress1 =service.save(address);
        return new ResponseEntity<>(adsress1, org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Address> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Address> update(@PathVariable Integer id,
                                          @Valid @RequestBody Address adsress) {
        return ResponseEntity.ok(service.update(id, adsress));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}

