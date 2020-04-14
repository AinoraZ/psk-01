package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.*;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.*;

@Model
public class PersonDetails implements Serializable {
    @Inject
    private PersonDAO personDAO;

    @Inject
    private LocationDAO locationDAO;

    @Inject
    private CoronaCaseDAO coronaCaseDAO;

    @Inject
    private PersonLocationDAO personLocationDAO;

    @Getter @Setter
    private Person person;

    @Getter @Setter
    private CoronaCase coronaToAdd = new CoronaCase();

    @Getter
    private List<Location> allLocations;

    @Getter @Setter
    private PersonLocation personLocationToAdd = new PersonLocation();

    @Getter @Setter
    private Integer location_id;

    @Getter @Setter
    private String update_name;

    @Getter @Setter
    private String update_surname;

    @Getter @Setter
    private String update_email;

    @Getter @Setter
    private String email_hint;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer personId = Integer.parseInt(requestParameters.get("personId"));
        this.person = this.personDAO.findOne(personId);
        this.email_hint = censorEmail(this.person.getEmail());
        this.allLocations = this.locationDAO.loadAll();
    }

    private String censorEmail(String email) {
        String[] split = email.split("@");
        return split[0].charAt(0) + "***" + split[0].charAt(split[0].length() - 1) + "@" + split[1];
    }

    @Transactional
    @LoggedInvocation
    public String addPersonLocation() {
        if (this.personLocationToAdd.getDateVisited().compareTo(new Date()) > 0){
            pushMessage("Cannot set date to future");
            return null;
        }

        this.personLocationToAdd.setPerson(this.person);
        Location location = this.locationDAO.findOne(this.location_id);
        this.personLocationToAdd.setLocation(location);

        this.personLocationDAO.persist(this.personLocationToAdd);
        return "personDetails?faces-redirect=true&personId=" + this.person.getId();
    }

    private void pushMessage(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    @Transactional
    @LoggedInvocation
    public String logCoronaOnPerson() {
        if (this.person.hasCorona()) {
            pushMessage("This person already has corona");
            return null;
        }

        if (this.coronaToAdd.getDateDiscovered().compareTo(new Date()) > 0){
            pushMessage("Cannot set date to future");
            return null;
        }

        coronaToAdd.setPerson(this.person);
        coronaCaseDAO.persist(coronaToAdd);

        return "personDetails?faces-redirect=true&personId=" + this.person.getId();
    }

    @Transactional
    @LoggedInvocation
    public String updatePerson() {
        boolean needs_update = false;
        if (update_name != null && !update_name.isBlank()){
            person.setName(update_name);
            needs_update = true;
        }

        if (update_surname != null && !update_name.isBlank()){
            person.setSurname(update_surname);
            needs_update = true;
        }

        if (update_email != null && !update_email.isBlank() && update_email.contains("@")){ //TODO: Check email format
            person.setEmail(update_email);
            needs_update = true;
        }

        if (!needs_update){
            pushMessage("Nothing to update!");
            return null;
        }

        try {
            personDAO.update(person);
            personDAO.forceUpdate();
        } catch(OptimisticLockException ex) {
            pushMessage("Another user is editing this person.");
            return null;
        } catch (PersistenceException ex) {
            pushMessage("Failed saving to db.");
            return null;
        }

        return "personDetails?faces-redirect=true&personId=" + this.person.getId();
    }
}