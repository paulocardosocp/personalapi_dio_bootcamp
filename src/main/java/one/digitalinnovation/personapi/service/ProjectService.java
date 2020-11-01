package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.ProjectDTO;
import one.digitalinnovation.personapi.entity.Project;
import one.digitalinnovation.personapi.mapper.ProjectMapper;
import one.digitalinnovation.personapi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private ProjectMapper projectMapper = ProjectMapper.INSTANCE;

    public MessageResponseDTO createProject(ProjectDTO projectDTO) {
        Project projectToSave = projectMapper.toModel(projectDTO);
        Project projectSaved = projectRepository.save(projectToSave);

        return MessageResponseDTO
                .builder()
                .message("Criado projeto com ID " + projectSaved.getId())
                .build();
    }

}
