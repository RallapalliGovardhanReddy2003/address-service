package com.example.addressservice.client;
import com.example.addressservice.fallback.EmployeeClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Map;

@FeignClient(name = "EMPLOYEE-SERVICE",fallback = EmployeeClientFallback.class)

public interface EmployeeClient {

    @GetMapping("/employees/{id}")
    Map<String, Object> getEmployeeById(@PathVariable Integer id);

}
