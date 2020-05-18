package lt.vu.services;
import lt.vu.persistence.TransactionEM;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EmailingServiceImpl implements EmailingService, Serializable {
    @Inject @TransactionEM
    private EntityManager em;

    protected List<Emailee> getAffectedPeople(int sickUserId) {
        String nativeQuery = "" +
                "SELECT DISTINCT person.ID, person.EMAIL FROM PERSON person " +
                "    LEFT JOIN PERSON_LOCATION PL ON person.ID = PL.PERSON_ID " +
                "    LEFT JOIN CORONA_CASE CC ON person.ID = CC.PERSON_ID " +
                "    WHERE person.ID != " + sickUserId + " AND " +
                "          CC.ID IS NULL AND " +
                "          PL.LOCATION_ID IN " +
                "               (SELECT DISTINCT LOCATION_ID FROM PERSON_LOCATION AS PL2 WHERE PL2.PERSON_ID =" + sickUserId + ")";

        Query query = em.createNativeQuery(nativeQuery);
        List<Object[]> affectedPeople = query.getResultList();

        return affectedPeople.stream().map((people) -> new Emailee((Integer) people[0], people[1].toString())).collect(Collectors.toList());
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Integer sendEmailsToContacting(int sickUserId) {
        List<Emailee> affectedPeople = this.getAffectedPeople(sickUserId);
        Integer mailedPeople = 0;

        for(Emailee emailee: affectedPeople) {
            try {
                System.out.println("Mailed person using corona-no-reply@" + getMailHost() + ".com at email: " + emailee.getEmail());
                handleEmailing(emailee);
                mailedPeople++;
            } catch (InterruptedException e) {
                return mailedPeople;
            }
        }

        return  mailedPeople;
    }

    public abstract void handleEmailing(Emailee emailee) throws InterruptedException;

    public abstract String getMailHost();
}