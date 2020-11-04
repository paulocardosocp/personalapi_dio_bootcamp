package one.digitalinnovation.personapi.mapper;

import one.digitalinnovation.personapi.dto.resquest.PersonProjectDTO;
import one.digitalinnovation.personapi.entity.PersonProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonProjectMapper {

    PersonProjectMapper INSTANCE = Mappers.getMapper(PersonProjectMapper.class);

    @Mapping(target = "person.birthDate", source = "person.birthDate", dateFormat = "dd-MM-yyyy")
    PersonProject toModel(PersonProjectDTO personProjectDTO);

    PersonProjectDTO toDTO(PersonProject project);
}
