package one.digitalinnovation.personapi.dto.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonProjectDTO {

    private Long id;

    private PersonDTO person;

    private ProjectDTO project;

    private boolean manager;
}
