package one.digitalinnovation.personapi.repository;

import one.digitalinnovation.personapi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
