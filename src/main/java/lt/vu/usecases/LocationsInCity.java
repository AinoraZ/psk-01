package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
import lt.vu.entities.Location;
import lt.vu.entities.Person;
import lt.vu.interceptors.CaughtInvocation;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CityDAO;
import lt.vu.persistence.LocationDAO;
import lt.vu.persistence.PersonDAO;


@Model
@RequestScoped
public class LocationsInCity implements Serializable {
    @Inject
    private CityDAO cityDAO;

    @Inject
    private LocationDAO locationDAO;

    @Inject
    private PersonDAO personDAO;

    @Getter @Setter
    private City city;

    @Getter @Setter
    private Location locationToCreate = new Location();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer cityId = Integer.parseInt(requestParameters.get("cityId"));
        this.city = cityDAO.findOne(cityId);
    }

    @Transactional
    @LoggedInvocation
    @CaughtInvocation
    public String createLocation() {
        locationToCreate.setCity(city);
        locationDAO.persist(locationToCreate);
        return "locations?faces-redirect=true&cityId=" + this.city.getId();
    }

    @CaughtInvocation
    public List<Person> getInfectedPeopleInLocation(Location location) {
        return personDAO.findInfectedByLocation(location.getId());
    }
}