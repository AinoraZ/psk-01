package lt.vu.persistence;

import lt.vu.entities.City;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CityDAO {
    @Inject
    private EntityManager em;

    public void persist(City city){
        this.em.persist(city);
    }

    public City findOne(Integer id){
        return em.find(City.class, id);
    }

    public City findOneByName(String name){
        return em.createNamedQuery("City.findByName", City.class)
            .setParameter("city_name", name)
            .getSingleResult();
    }

    public List<City> loadAll() {
        return em.createNamedQuery("City.findAll", City.class)
            .getResultList();
    }

    public City update(City city){
        return em.merge(city);
    }
}
