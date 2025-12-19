package com.example.addressservice.repository;
import com.example.addressservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {


    List<Address> findByEmployeeId(Integer employeeId);
}

