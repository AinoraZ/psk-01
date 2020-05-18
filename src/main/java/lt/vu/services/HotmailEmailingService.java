package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;

@ApplicationScoped
@Alternative
public class HotmailEmailingService extends EmailingServiceImpl implements Serializable {
    @Override
    public void handleEmailing(Emailee emailee) throws InterruptedException {
        Thread.sleep(1000); // Simulate mailing
    }

    @Override
    public String getMailHost() {
        return "Hotmail";
    }
}