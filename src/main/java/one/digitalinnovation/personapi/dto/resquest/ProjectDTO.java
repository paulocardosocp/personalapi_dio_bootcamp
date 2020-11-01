package one.digitalinnovation.personapi.dto.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.entity.Department;
import one.digitalinnovation.personapi.entity.PersonProject;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;

    private String title;

    private Department department;

    private List<PersonProject> personProject;
}
