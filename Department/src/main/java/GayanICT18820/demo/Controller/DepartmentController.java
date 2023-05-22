package GayanICT18820.demo.Controller;

import GayanICT18820.demo.Services.DepartmentService;
import GayanICT18820.demo.entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deparmets")
public class DepartmentController {
    @Autowired
    private DepartmentService DepartmentService;

    @PostMapping
    public DepartmentEntity create(@RequestBody DepartmentEntity department) {
        return DepartmentService.AddNewDepartment(department);
    }
    @GetMapping
    public List<DepartmentEntity> findAll() {
        return DepartmentService.GetAll();
    }

    @GetMapping("/{id}")
    public Optional<DepartmentEntity> findById(@PathVariable Long id) {
        return DepartmentService.FindDepartmentById(id);
    }

    @PutMapping("/{id}")
    public DepartmentEntity updateDepartment(@PathVariable Long id, @RequestBody DepartmentEntity updatedDepartment) {
        return DepartmentService.UpdateDepartmentById(id, updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable Long id) {
        DepartmentService.DeleteDepartmentById(id);
    }

}
