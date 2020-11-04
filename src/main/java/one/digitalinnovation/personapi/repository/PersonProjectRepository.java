package one.digitalinnovation.personapi.repository;

import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.entity.PersonProject;
import one.digitalinnovation.personapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonProjectRepository extends JpaRepository<PersonProject, Long> {

    List<PersonProject> findByProject(Project project);

    List<PersonProject> findByPerson(Person person);
}
