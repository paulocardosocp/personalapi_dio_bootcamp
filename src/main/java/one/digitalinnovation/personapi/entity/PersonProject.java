package one.digitalinnovation.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonProject {

    @EmbeddedId
    private PersonProjectPK id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID", insertable = false, updatable = false)
    @MapsId("personId")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID", insertable = false, updatable = false)
    @MapsId("projectId")
    private Project project;

    @Column(nullable = false)
    private boolean manager;
}
