package lt.vu.persistence;

import lt.vu.entities.CoronaCase;
import lt.vu.entities.PersonLocation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PersonLocationDAO {
    @Inject
    private EntityManager em;

    public void persist(PersonLocation personLocation){
        this.em.persist(personLocation);
    }
}
