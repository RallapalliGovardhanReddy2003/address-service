package com.example.addressservice.fallback;



import com.example.addressservice.client.EmployeeClient;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component   // 🔴 VERY IMPORTANT
public class EmployeeClientFallback implements EmployeeClient {

    @Override
    public Map<String, Object> getEmployeeById(Integer id) {

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Employee Service is DOWN");
        map.put("employeeId", id);
        map.put("status", "fallback-response");

        return map;
    }
}

