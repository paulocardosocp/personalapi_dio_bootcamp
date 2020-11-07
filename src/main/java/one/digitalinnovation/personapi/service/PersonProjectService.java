package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.PersonDTO;
import one.digitalinnovation.personapi.dto.resquest.PersonProjectDTO;
import one.digitalinnovation.personapi.dto.resquest.ProjectDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.entity.PersonProject;
import one.digitalinnovation.personapi.entity.Project;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.exception.ProjectNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonProjectMapper;
import one.digitalinnovation.personapi.repository.PersonProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que representa o serviço que possui as operações
 * que abrangem simultaneamente pessoa e projeto.
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonProjectService {

    private PersonProjectRepository personProjectRepository;

    private ProjectService projectService;

    private PersonService personService;

    private final PersonProjectMapper personProjectMapper = PersonProjectMapper.INSTANCE;

    /**
     * Inclui pessoas em um projeto depois de verificar se todas as pessoas e o projeto
     * foram previamente cadastrados
     * @param personProjectDTOList Lista contendo as pessoas e o projeto
     * @return
     * @throws PersonNotFoundException
     * @throws ProjectNotFoundException
     */
    public MessageResponseDTO addPeopleToProject(List<PersonProjectDTO> personProjectDTOList)
            throws PersonNotFoundException, ProjectNotFoundException {

        for (PersonProjectDTO personProjectDTO : personProjectDTOList) {

            personService.verifyIfExists(personProjectDTO.getPerson().getId());
            Person person = personService.personMapper.toModel(personProjectDTO.getPerson());

            projectService.verifyIfExists(personProjectDTO.getProject().getId());
            Project project = projectService.projectMapper.toModel(personProjectDTO.getProject());

            PersonProject personProject = personProjectMapper.toModel(personProjectDTO);
            personProject.setPerson(person);
            personProject.setProject(project);

            personProjectRepository.save(personProject);
        }

        return MessageResponseDTO
                .builder()
                .message("Pessoas adicionadas ao projeto " + personProjectDTOList.get(0).getProject().getTitle())
                .build();
    }

    /**
     * Lista as pessoas pertencentes a um projeto
     * @param idProject id do projeto para busca das pessoas
     * @return Lista com as pessoas do projeto
     * @throws ProjectNotFoundException
     */
    public List<PersonDTO> listPeopleByProject(Long idProject) throws ProjectNotFoundException {
        Project project = projectService.verifyIfExists(idProject);
        List<PersonProject> personProjectList = personProjectRepository.findByProject(project);

        return personProjectList.stream()
                .map(personProject -> personService.personMapper.toDTO(personProject.getPerson()))
                .collect(Collectors.toList());
    }

    /**
     * Lista os projetos por pessoa
     * @param idPerson id da pessoa para busca dos projetos
     * @return Lista com os projetos da pessoa
     * @throws PersonNotFoundException
     */
    public List<ProjectDTO> listProjectsByPerson(Long idPerson) throws PersonNotFoundException {
        Person person = personService.verifyIfExists(idPerson);
        List<PersonProject> personProjectList = personProjectRepository.findByPerson(person);

        return personProjectList.stream()
                .map(personProject -> projectService.projectMapper.toDTO(personProject.getProject()))
                .collect(Collectors.toList());
    }
}
