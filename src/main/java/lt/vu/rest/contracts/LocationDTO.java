package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.City;
import lt.vu.entities.Location;
import lt.vu.entities.PersonLocation;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class LocationDTO {
    public LocationDTO() {
    }

    public LocationDTO(Location location) {
        id = location.getId();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        name = location.getName();
        street = location.getStreet();
        cityId = location.getCity().getId();
    }

    private Integer id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String name;
    private String street;
    private Integer cityId;
}
