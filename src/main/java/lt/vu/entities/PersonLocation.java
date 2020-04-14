package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PERSON_LOCATION")
@Getter
@Setter
@NoArgsConstructor
public class PersonLocation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "date_visited")
    private Date dateVisited;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonLocation personLocation = (PersonLocation) o;
        return Objects.equals(id, personLocation.id) &&
                Objects.equals(person, personLocation.person) &&
                Objects.equals(dateVisited, personLocation.dateVisited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateVisited);
    }
}