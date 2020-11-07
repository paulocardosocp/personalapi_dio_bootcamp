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

/**
 * Controlador da API com os endpoints referentes a
 * departamentos.
 */
@RestController
@RequestMapping("/api/v2/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * Cadastra um departamento.
     * @param departmentDTO Dados do departamento
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        return departmentService.createDepartment(departmentDTO);
    }

    /**
     * Altera os dados de um departamento.
     * @param departmentDTO Dados alterados do departamento
     * @param id Id do departamento a ser alterado
     * @return
     * @throws DepartmentNotFoundException
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long id)
            throws DepartmentNotFoundException {
        return departmentService.updateDepartment(departmentDTO, id);
    }

    /**
     * Lista todos os departamentos.
     * @return lista com os departamentos
     */
    @GetMapping
    public List<DepartmentDTO> listAll() {
        return departmentService.listAll();
    }

    /**
     * Busca um departamento pelo id.
     * @param id
     * @return departamento encontrado
     * @throws DepartmentNotFoundException
     */
    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable Long id) throws DepartmentNotFoundException {
        return departmentService.findById(id);
    }

    /**
     * Exclui um departamento pelo id.
     * @param id
     * @throws DepartmentNotFoundException
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws DepartmentNotFoundException {
        departmentService.deleteById(id);
    }
}
