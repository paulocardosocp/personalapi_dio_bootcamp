package one.digitalinnovation.personapi.mapper;

import one.digitalinnovation.personapi.dto.resquest.ProjectDTO;
import one.digitalinnovation.personapi.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Interface que traduz a classe entidade Project em uma classe DTO
 * equivalente e vice-versa.
 */
@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project toModel(ProjectDTO projectDTO);

    ProjectDTO toDTO(Project project);
}
