package one.digitalinnovation.personapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.PersonDTO;
import one.digitalinnovation.personapi.dto.resquest.PersonProjectDTO;
import one.digitalinnovation.personapi.dto.resquest.ProjectDTO;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.exception.ProjectNotFoundException;
import one.digitalinnovation.personapi.service.PersonProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/person-project")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonProjectController {

    private PersonProjectService personProjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO addPeopleToProject(@RequestBody List<PersonProjectDTO> personProjectDTOList)
            throws PersonNotFoundException, ProjectNotFoundException {
        return personProjectService.addPeopleToProject(personProjectDTOList);
    }

    @GetMapping("/project/{idProject}")
    public List<PersonDTO> listPeopleByProject(@PathVariable Long idProject) throws ProjectNotFoundException {
        return personProjectService.listPeopleByProject(idProject);
    }

    @GetMapping("/person/{idPerson}")
    public List<ProjectDTO> listProjectsByPerson(@PathVariable Long idPerson) throws PersonNotFoundException {
        return personProjectService.listProjectsByPerson(idPerson);
    }
}
