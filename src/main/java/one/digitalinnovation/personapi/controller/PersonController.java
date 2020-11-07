package one.digitalinnovation.personapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.PersonDTO;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador da API com os endpoints referentes a
 * pessoas.
 */
@RestController
@RequestMapping("/api/v2/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    /**
     * Cadastra uma pessoa.
     * @param personDTO Dados da pessoa
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    /**
     * Altera os dados de uma pessoa.
     * @param personDTO Dados alterados da pessoa
     * @param id Id da pessoa a ser alterada
     * @return
     * @throws PersonNotFoundException
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updatePerson(@RequestBody @Valid PersonDTO personDTO, @PathVariable Long id)
            throws PersonNotFoundException {
        return personService.updatePerson(personDTO, id);
    }

    /**
     * Lista todas as pessoas.
     * @return lista das pessoas
     */
    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    /**
     * Busca uma pessoa pelo id.
     * @param id
     * @return Pessoa encontrada
     * @throws PersonNotFoundException
     */
    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    /**
     * Exclui uma pessoa pelo id.
     * @param id
     * @throws PersonNotFoundException
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
