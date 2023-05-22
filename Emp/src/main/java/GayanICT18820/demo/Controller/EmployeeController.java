package GayanICT18820.demo.Controller;

import GayanICT18820.demo.Services.EmployeeService;
import GayanICT18820.demo.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeEntity create(@RequestBody EmployeeEntity student) {
        return employeeService.AddNewEmployee(student);
    }
    @GetMapping
    public List<EmployeeEntity> findAll() {
        return employeeService.GetAll();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeEntity> findById(@PathVariable Long id) {
        return employeeService.FindEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity updatedStudent) {
        return employeeService.UpdateEmployeeById(id, updatedStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.DeleteEmployeeById(id);
    }

}
