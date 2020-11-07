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

/**
 * Controlador da API com os endpoints referentes a
 * pessoas e projetos simultaneamente.
 */
@RestController
@RequestMapping("/api/v2/person-project")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonProjectController {

    private PersonProjectService personProjectService;

    /**
     * Inclui pessoas em um projeto.
     * @param personProjectDTOList Lista contendo as pessoas e o projeto
     * @return
     * @throws PersonNotFoundException
     * @throws ProjectNotFoundException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO addPeopleToProject(@RequestBody List<PersonProjectDTO> personProjectDTOList)
            throws PersonNotFoundException, ProjectNotFoundException {
        return personProjectService.addPeopleToProject(personProjectDTOList);
    }

    /**
     * Lista as pessoas pertencentes a um projeto
     * @param idProject id do projeto para busca das pessoas
     * @return Lista com as pessoas do projeto
     * @throws ProjectNotFoundException
     */
    @GetMapping("/project/{idProject}")
    public List<PersonDTO> listPeopleByProject(@PathVariable Long idProject) throws ProjectNotFoundException {
        return personProjectService.listPeopleByProject(idProject);
    }

    /**
     * Lista os projetos por pessoa
     * @param idPerson id da pessoa para busca dos projetos
     * @return Lista com os projetos da pessoa
     * @throws PersonNotFoundException
     */
    @GetMapping("/person/{idPerson}")
    public List<ProjectDTO> listProjectsByPerson(@PathVariable Long idPerson) throws PersonNotFoundException {
        return personProjectService.listProjectsByPerson(idPerson);
    }
}
