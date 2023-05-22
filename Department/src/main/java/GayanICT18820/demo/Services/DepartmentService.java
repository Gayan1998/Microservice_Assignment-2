package GayanICT18820.demo.Services;

import GayanICT18820.demo.entity.DepartmentEntity;
import GayanICT18820.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentEntity AddNewDepartment(DepartmentEntity department) {
        return departmentRepository.save(department);
    }

    public List<DepartmentEntity> GetAll() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentEntity> FindDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }
    public DepartmentEntity UpdateDepartmentById(Long id, DepartmentEntity updatedDepartment) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(updatedDepartment.getName());
                    department.setDescription(updatedDepartment.getDescription());
                    return departmentRepository.save(department);
                })
                .orElseThrow(() -> new RuntimeException("Department with ID " + id + " not found"));
    }

    public void DeleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
