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

    @Column(nullable = false)
    private boolean manager;
}
