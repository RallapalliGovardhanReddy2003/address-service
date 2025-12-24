package com.example.addressservice.controller;
import com.example.addressservice.entity.Address;
import com.example.addressservice.service.AddressService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "http://localhost:4300")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Address> create(@Validated @RequestBody Address address) {
        Address addsress =service.save(address);
        return new ResponseEntity<>(addsress, HttpStatus.CREATED);
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
                                          @Validated @RequestBody Address address) {
        return ResponseEntity.ok(service.update(id, address));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
    @GetMapping("/alladdresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = service.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }
    @GetMapping("/employees/{employeeId}")
    public Map<String,Object> getEmployeeWithAddress(@PathVariable("employeeId") Integer employeeId){
        return service.getEmployeeWithAddress(employeeId);
    }





}

