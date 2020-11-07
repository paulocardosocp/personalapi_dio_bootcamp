package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    public final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        /* codigo refatorado
        Person personToSave = Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .birthDate(personDTO.getBirthDate()) //ainda teria que ser feita a conversao DateToString
                .build();
        */
        return this.save(personDTO, "Pessoa criada com o ID ");
    }

    public MessageResponseDTO updatePerson(PersonDTO personDTO, Long id) throws PersonNotFoundException {
        this.verifyIfExists(id);
        return this.save(personDTO, "Pessoa atualizada com ID ");
    }

    public List<PersonDTO> listAll() {
        /* codigo refatorado
        List<PersonDTO> personDTOList = new ArrayList<>();
        List<Person> personList = personRepository.findAll();
        for (Person person : personList) {
            PersonDTO personDTO = personMapper.toDTO(person);
            personDTOList.add(personDTO);
        }
        */
        return personRepository.findAll().stream()
                .map(personMapper::toDTO) //.map(person -> personMapper.toDTO(person))
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        /* codigo refatorado
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new PersonNotFoundException(id);
        }*/
        Person person = this.verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        this.verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO save(PersonDTO personDTO, String message) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message(message + savedPerson.getId())
                .build();
    }

}
