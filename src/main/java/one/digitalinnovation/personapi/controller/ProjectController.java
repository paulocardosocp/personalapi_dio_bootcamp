package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.ProjectDTO;
import one.digitalinnovation.personapi.exception.ProjectNotFoundException;
import one.digitalinnovation.personapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador da API com os endpoints referentes a
 * projetos.
 */
@RestController
@RequestMapping("/api/v2/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * Cadastra um novo projeto.
     * @param projectDTO Dados do projeto
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createProject(@RequestBody @Valid ProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }

    /**
     * Altera os dados de um projeto
     * @param projectDTO Dados alterados do projeto
     * @param id Para busca pelo id
     * @return
     * @throws ProjectNotFoundException
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateProject(@RequestBody @Valid ProjectDTO projectDTO, @PathVariable Long id)
            throws ProjectNotFoundException {
        return projectService.updateProject(projectDTO, id);
    }

    /**
     * Lista todos os projetos.
     * @return lista de projetos
     */
    @GetMapping
    public List<ProjectDTO> listAll() {
        return projectService.listAll();
    }

    /**
     * Busca um projeto pelo id.
     * @param id
     * @return Projeto encontrado
     * @throws ProjectNotFoundException
     */
    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable Long id) throws ProjectNotFoundException {
        return projectService.findById(id);
    }

    /**
     * Exclui um projeto pelo id.
     * @param id
     * @throws ProjectNotFoundException
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ProjectNotFoundException {
        projectService.deleteById(id);
    }

}
