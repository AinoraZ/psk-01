package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "CoronaCase.findAll", query = "select a from CoronaCase as a")
})
@Table(name = "CORONA_CASE")
@Getter
@Setter
@NoArgsConstructor
public class CoronaCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "date_discovered")
    private Date dateDiscovered;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoronaCase corona = (CoronaCase) o;
        return Objects.equals(id, corona.id) &&
                Objects.equals(person, corona.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person);
    }
}