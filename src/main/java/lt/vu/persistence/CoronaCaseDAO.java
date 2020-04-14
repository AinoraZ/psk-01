package lt.vu.persistence;

import lt.vu.entities.City;
import lt.vu.entities.CoronaCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CoronaCaseDAO {
    @Inject
    private EntityManager em;

    public void persist(CoronaCase corona){
        this.em.persist(corona);
    }
}
