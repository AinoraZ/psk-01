package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.CityMapper;
import lt.vu.mybatis.dao.LocationMapper;
import lt.vu.mybatis.model.City;
import lt.vu.mybatis.model.Location;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;


@Model
public class LocationsInCityMyBatis implements Serializable {
    @Inject
    private CityMapper cityMapper;

    @Inject
    private LocationMapper locationMapper;

    @Getter @Setter
    private City city;

    @Getter @Setter
    private Location locationToCreate = new Location();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer cityId = Integer.parseInt(requestParameters.get("cityId"));
        this.city = cityMapper.selectByPrimaryKey(cityId);
    }

    @Transactional
    @LoggedInvocation
    public String createLocation() {
        locationToCreate.setCityId(city.getId());
        locationMapper.insert(locationToCreate);
        return "locations?faces-redirect=true&cityId=" + this.city.getId();
    }
}