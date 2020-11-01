package one.digitalinnovation.personapi.repository;

import one.digitalinnovation.personapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
