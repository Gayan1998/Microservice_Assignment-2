package GayanICT18820.demo.Controller;

import com.netflix.discovery.EurekaClient;
import GayanICT18820.demo.entity.AdminEntity;
import GayanICT18820.demo.entity.DepartmentEntity;
import GayanICT18820.demo.entity.EmployeeEntity;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EurekaClient discoveryClient;

    // RestTemplate to make HTTP requests
    private RestTemplate restTemplate = new RestTemplate();

    // Employee endpoints

    // Create a new Employee
    @PostMapping("/employees")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {
        // Discover the Employee service
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("EMPLOYEE-SERVICE",false);
        // Construct the URL for the Employee service
        String empUrl = instanceInfo.getHomePageUrl() + "/employees";
        // Send a POST request to the Employee service to create a new employee
        return restTemplate.postForObject(empUrl,employee, EmployeeEntity.class);
    }

    // Retrieve all Employees
    @GetMapping("/employees")
    @HystrixCommand(fallbackMethod = "findAllEmployeesFallback")
    public List<EmployeeEntity> findAllEmployees() {
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("EMPLOYEE-SERVICE", false);
        String empUrl = instanceInfo.getHomePageUrl() + "/employees";
        EmployeeEntity[] employees = restTemplate.getForObject(empUrl, EmployeeEntity[].class);
        return Arrays.asList(employees);
    }

    // Fallback method
    public List<EmployeeEntity> findAllEmployeesFallback(Throwable t) {
        return Collections.emptyList();
    }

    // Retrieve an Employee by ID
    @GetMapping("/employees/{id}")
    public EmployeeEntity findEmployeeById(@PathVariable Long id) {
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("EMPLOYEE-SERVICE",false);
        String empUrl = instanceInfo.getHomePageUrl() + "/employees/" + id;
        // Send a GET request to the Employee service to retrieve an employee by ID
        return restTemplate.getForObject(empUrl, EmployeeEntity.class);
    }

    // Department endpoints

    // Create a new Department
    @PostMapping("/departments")
    public DepartmentEntity createDepartment(@RequestBody DepartmentEntity department) {
        // Discover the Department service
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("DEPARTMENT-SERVICE",false);
        // Construct the URL for the Department service
        String depUrl = instanceInfo.getHomePageUrl() + "/departments";
        // Send a POST request to the Department service to create a new department
        return restTemplate.postForObject(depUrl,department, DepartmentEntity.class);
    }

    // Retrieve all Departments
    @GetMapping("/departments")
    public List<DepartmentEntity> findAllDepartments() {
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("DEPARTMENT-SERVICE",false);
        String depUrl = instanceInfo.getHomePageUrl() + "/departments";
        // Send a GET request to the Department service to retrieve all departments
        DepartmentEntity[] departments = restTemplate.getForObject(depUrl, DepartmentEntity[].class);
        return Arrays.asList(departments);
    }

    // Retrieve a Department by ID
    @GetMapping("/departments/{id}")
    public DepartmentEntity findDepartmentById(@PathVariable Long id) {
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("DEPARTMENT-SERVICE",false);
        String depUrl = instanceInfo.getHomePageUrl() + "/departments/" + id;
        // Send a GET request to the Department service to retrieve a department by ID
        return restTemplate.getForObject(depUrl, DepartmentEntity.class);
    }

}
