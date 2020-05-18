package lt.vu.services;

public interface EmailingService {
    Integer sendEmailsToContacting(int sickUserId);
    void handleEmailing(Emailee emailee) throws InterruptedException;
    String getMailHost();
}
