package GayanICT18820.demo.Services;

import GayanICT18820.demo.entity.EmployeeEntity;
import GayanICT18820.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity AddNewEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    public List<EmployeeEntity> GetAll() {
        return employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> FindEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    public EmployeeEntity UpdateEmployeeById(Long id, EmployeeEntity updatedEmployee) {
        return employeeRepository.findById(id)
                .map(Employee -> {
                    Employee.setFirstName(updatedEmployee.getFirstName());
                    Employee.setLastName(updatedEmployee.getLastName());
                    return employeeRepository.save(Employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));
    }

    public void DeleteEmployeeById(Long id) {employeeRepository.deleteById(id);
    }
}
