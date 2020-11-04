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

@RestController
@RequestMapping("/api/v2/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createProject(@RequestBody @Valid ProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateProject(@RequestBody @Valid ProjectDTO projectDTO, @PathVariable Long id)
            throws ProjectNotFoundException {
        return projectService.updateProject(projectDTO, id);
    }

    @GetMapping
    public List<ProjectDTO> listAll() {
        return projectService.listAll();
    }

    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable Long id) throws ProjectNotFoundException {
        return projectService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ProjectNotFoundException {
        projectService.deleteById(id);
    }

}
