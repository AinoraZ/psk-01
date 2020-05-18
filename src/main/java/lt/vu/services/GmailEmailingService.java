package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;

@ApplicationScoped
@Default
public class GmailEmailingService extends EmailingServiceImpl implements Serializable {
    @Override
    public void handleEmailing(Emailee emailee) throws InterruptedException {
        Thread.sleep(500); // Simulate mailing
    }

    @Override
    public String getMailHost() {
        return "Gmail";
    }
}