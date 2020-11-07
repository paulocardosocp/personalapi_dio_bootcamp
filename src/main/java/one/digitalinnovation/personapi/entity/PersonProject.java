package one.digitalinnovation.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Classe que representa a entidade surgida com o
 * relacionamento N:M entre Person e Project
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(nullable = false)
    private boolean manager;
}
