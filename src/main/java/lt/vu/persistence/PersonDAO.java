package lt.vu.persistence;

import lt.vu.entities.City;
import lt.vu.entities.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PersonDAO {
    @Inject
    private EntityManager em;

    public void persist(Person person){
        this.em.persist(person);
    }

    public Person findOne(Integer id){
        return em.find(Person.class, id);
    }

    public List<Person> loadAll() {
        return em.createNamedQuery("Person.findAll", Person.class)
            .getResultList();
    }

    public List<Person> findAllByCity(Integer city_id){
        return em.createNamedQuery("Person.findByCity", Person.class)
            .setParameter("city_id", city_id)
            .getResultList();
    }

    public List<Person> findInfectedByLocation(Integer location_id) {
        return em.createNamedQuery("Person.findInfectedByLocation", Person.class)
            .setParameter("location_id", location_id)
            .getResultList();
    }

    public void forceUpdate() {
        em.flush();
    }

    public Person update(Person person){
        return em.merge(person);
    }
}
