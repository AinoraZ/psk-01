package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.City;
import lt.vu.persistence.CityDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Cities {
    @Inject
    private CityDAO cityDAO;

    @Getter @Setter
    private City cityToCreate = new City();

    @Getter
    private List<City> allCities;

    @PostConstruct
    public void init(){
        this.allCities = cityDAO.loadAll();
    }

    @Transactional
    public String createCity(){
        this.cityDAO.persist(cityToCreate);
        return "index?faces-redirect=true";
    }
}