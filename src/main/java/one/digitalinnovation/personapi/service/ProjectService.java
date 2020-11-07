package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.ProjectDTO;
import one.digitalinnovation.personapi.entity.Project;
import one.digitalinnovation.personapi.exception.ProjectNotFoundException;
import one.digitalinnovation.personapi.mapper.ProjectMapper;
import one.digitalinnovation.personapi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectService {

    private ProjectRepository projectRepository;

    public final ProjectMapper projectMapper = ProjectMapper.INSTANCE;

    public MessageResponseDTO createProject(ProjectDTO projectDTO) {
        return this.save(projectDTO, "Criado projeto com ID ");
    }

    public MessageResponseDTO updateProject(ProjectDTO projectDTO, Long id) throws ProjectNotFoundException {
        this.verifyIfExists(id);
        return this.save(projectDTO, "Atualizado projeto com ID ");
    }

    public List<ProjectDTO> listAll() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO findById(Long id) throws ProjectNotFoundException {
        Project project = this.verifyIfExists(id);
        return projectMapper.toDTO(project);
    }

    public void deleteById(Long id) throws ProjectNotFoundException {
        this.verifyIfExists(id);
        projectRepository.deleteById(id);
    }

    public Project verifyIfExists(Long id) throws ProjectNotFoundException {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    private MessageResponseDTO save(ProjectDTO projectDTO, String message) {
        Project projectToSave = projectMapper.toModel(projectDTO);
        Project savedProject = projectRepository.save(projectToSave);

        return MessageResponseDTO
                .builder()
                .message(message + savedProject.getId())
                .build();
    }
}
