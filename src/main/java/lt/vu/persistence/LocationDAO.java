package lt.vu.persistence;

import lt.vu.entities.City;
import lt.vu.entities.Location;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LocationDAO {
    @Inject
    private EntityManager em;

    public void persist(Location location){
        this.em.persist(location);
    }

    public Location findOne(Integer id){
        return em.find(Location.class, id);
    }

    public List<Location> loadAll() {
        return em.createNamedQuery("Location.findAll", Location.class)
                .getResultList();
    }

    public Location update(Location location){
        return em.merge(location);
    }
}
