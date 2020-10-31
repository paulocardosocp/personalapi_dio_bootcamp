package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.DepartmentDTO;
import one.digitalinnovation.personapi.exception.DepartmentNotFoundException;
import one.digitalinnovation.personapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v2/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        return departmentService.createDepartment(departmentDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long id)
            throws DepartmentNotFoundException {
        return departmentService.updateDepartment(departmentDTO, id);
    }

    @GetMapping
    public List<DepartmentDTO> listAll() {
        return departmentService.listAll();
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable Long id) throws DepartmentNotFoundException {
        return departmentService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws DepartmentNotFoundException {
        departmentService.deleteById(id);
    }
}
