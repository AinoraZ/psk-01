package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
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
import lt.vu.services.EmailFormatChecker;


@Model
public class People implements Serializable {
    @Inject
    private PersonDAO personDAO;

    @Inject
    EmailFormatChecker emailChecker;

    @Getter @Setter
    private Person personToAdd = new Person();

    @Getter
    private List<Person> allPeople;

    @PostConstruct
    public void init() {
        this.allPeople = personDAO.loadAll();
    }

    private void pushMessage(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    @Transactional
    @LoggedInvocation
    @CaughtInvocation
    public String createPerson() {
        if (personToAdd.getName().isBlank() || personToAdd.getSurname().isBlank()) {
            pushMessage("Name and/or surname must not be blank");
            return null;
        }

        if (!emailChecker.isValid(personToAdd.getEmail())){
            pushMessage("Email must have valid formatting");
            return null;
        }

        personDAO.persist(personToAdd);
        return "index?faces-redirect=true";
    }
}