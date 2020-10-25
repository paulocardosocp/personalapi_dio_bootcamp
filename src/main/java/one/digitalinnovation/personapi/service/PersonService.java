package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.resquest.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO) {

        /* codigo substituido
        Person personToSave = Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .birthDate(personDTO.getBirthDate()) //ainda teria que ser feita a conversao DateToString
                .build();
        */
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Pessoa criada com ID " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        /* codigo substituido
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
}
