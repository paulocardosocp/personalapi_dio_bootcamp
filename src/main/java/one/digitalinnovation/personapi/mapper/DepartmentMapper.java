package one.digitalinnovation.personapi.mapper;

import one.digitalinnovation.personapi.dto.resquest.DepartmentDTO;
import one.digitalinnovation.personapi.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Interface que traduz a classe entidade Departament em uma classe DTO
 * equivalente e vice-versa.
 */
@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE  = Mappers.getMapper(DepartmentMapper.class);

    Department toModel(DepartmentDTO departmentDTO);

    DepartmentDTO toDTO(Department department);
}

