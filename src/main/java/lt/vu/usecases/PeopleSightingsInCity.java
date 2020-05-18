package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.City;
import lt.vu.entities.Person;
import lt.vu.interceptors.CaughtInvocation;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CityDAO;
import lt.vu.persistence.PersonDAO;


@Model
public class PeopleSightingsInCity implements Serializable {
    @Inject
    private CityDAO cityDAO;

    @Inject
    private PersonDAO personDAO;

    @Getter @Setter
    private City city;

    @Getter
    private List<Person> cityPeople;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer cityId = Integer.parseInt(requestParameters.get("cityId"));
        this.city = cityDAO.findOne(cityId);
        this.cityPeople = personDAO.findAllByCity(cityId);
    }
}