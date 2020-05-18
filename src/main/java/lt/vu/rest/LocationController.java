package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PessimisticLockException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lt.vu.entities.City;
import lt.vu.entities.Location;
import lt.vu.interceptors.CaughtInvocation;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CityDAO;
import lt.vu.persistence.LocationDAO;
import lt.vu.rest.contracts.LocationDTO;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/location")
@LoggedInvocation
public class LocationController {
    @Inject
    @Setter @Getter
    private LocationDAO locationDAO;

    @Inject
    @Setter @Getter
    private CityDAO cityDAO;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Location> locations = locationDAO.loadAll();
        List<LocationDTO> locationsDto = locations.stream()
                .map((LocationDTO::new))
                .collect(Collectors.toList());

        return Response.ok(locationsDto).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Location location = locationDAO.findOne(id);
        if (location == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        LocationDTO locationDTO = new LocationDTO(location);
        return Response.ok(locationDTO).build();
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNewLocation(LocationDTO locationData) {
        try {
            Location location = new Location();
            location.setName(locationData.getName());
            location.setLongitude(locationData.getLongitude());
            location.setLatitude(locationData.getLatitude());
            location.setStreet(locationData.getStreet());

            City city = cityDAO.findOne(locationData.getCityId());
            if (city == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            location.setCity(city);

            locationDAO.persist(location);

            return Response.ok().build();
        } catch (OptimisticLockException | PessimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer playerId, LocationDTO locationData) {
        try {
            Location location = locationDAO.findOne(playerId);
            if (location == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            location.setLatitude(locationData.getLatitude());
            location.setLongitude(locationData.getLongitude());
            location.setName(locationData.getName());
            location.setStreet(locationData.getStreet());
            locationDAO.update(location);

            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
