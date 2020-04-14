package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "select a from Person as a"),
        @NamedQuery(name = "Person.findWithCorona", query = "select person from Person as person left join person.coronaCase as corona where corona is not null"),
        @NamedQuery(name = "Person.findByCity", query = "select distinct person from Person as person left join person.locations as personLocation where personLocation.location.city.id = :city_id"),
        @NamedQuery(name = "Person.findInfectedByLocation", query = "select distinct person from Person as person " +
                "left join person.coronaCase as corona left join person.locations as personLocation " +
                "where personLocation.location.id = :location_id AND corona is not null")
})
@Table(name = "PERSON")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String surname;

    @Size(max = 50)
    @Column(unique = true)
    private String email;

    @OneToOne(mappedBy = "person")
    private CoronaCase coronaCase;

    @OneToMany(mappedBy = "person")
    private List<PersonLocation> locations = new ArrayList<>();

    @Version
    @Column(name = "optimistic_lock_ver")
    private Integer version;

    public boolean hasCorona(){
        return coronaCase != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}