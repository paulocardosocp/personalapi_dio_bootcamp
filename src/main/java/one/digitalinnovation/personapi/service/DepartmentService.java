package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.DepartmentDTO;
import one.digitalinnovation.personapi.entity.Department;
import one.digitalinnovation.personapi.exception.DepartmentNotFoundException;
import one.digitalinnovation.personapi.mapper.DepartmentMapper;
import one.digitalinnovation.personapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper = DepartmentMapper.INSTANCE;

    public MessageResponseDTO createDepartment(DepartmentDTO departmentDTO) {
        return this.save(departmentDTO, "Departamento criado com ID ");
    }

    public MessageResponseDTO updateDepartment(DepartmentDTO departmentDTO, Long id)
            throws DepartmentNotFoundException {
        this.verifyIfExists(id);
        return this.save(departmentDTO, "Departamento atualizado com ID ");
    }

    public List<DepartmentDTO> listAll() {
        return departmentRepository.findAll()
                .stream().map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO findById(Long id) throws DepartmentNotFoundException {
        Department department = this.verifyIfExists(id);
        return departmentMapper.toDTO(department);
    }

    public void deleteById(Long id)
            throws DepartmentNotFoundException {
        this.verifyIfExists(id);
        departmentRepository.deleteById(id);
    }

    public Department verifyIfExists(Long id) throws DepartmentNotFoundException {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    private MessageResponseDTO save(DepartmentDTO departmentDTO, String message) {
        Department departmentToSave = departmentMapper.toModel(departmentDTO);
        Department savedDepartment = departmentRepository.save(departmentToSave);

        return MessageResponseDTO
                .builder()
                .message(message + savedDepartment.getId())
                .build();
    }

}
