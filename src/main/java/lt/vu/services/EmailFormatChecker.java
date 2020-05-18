package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailFormatChecker {
    public boolean isValid(String email) {
        return email != null && !email.isBlank() && email.contains("@");
    }
}
