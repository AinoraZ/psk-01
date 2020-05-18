package lt.vu.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class EmailLoggingDecorator implements EmailingService {
    @Inject @Delegate @Any
    EmailingService emailingService;

    public Integer sendEmailsToContacting(int sickUserId) {
        System.out.println("Mailing all people that were in contact with person with id " + sickUserId);
        return emailingService.sendEmailsToContacting(sickUserId);
    }
}


