package lt.vu.services;

import lt.vu.persistence.TransactionEM;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class EmailingService implements Serializable {
    @Inject @TransactionEM
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Integer sendEmailsToContacting(int sickUserId) {
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

        Integer mailedPeople = 0;
        for(Object[] person: affectedPeople) {
            try {
                Thread.sleep(500); // Simulate mailing
                System.out.println("Mailed person at email: " + person[1]);
                mailedPeople++;
            } catch (InterruptedException e) {
                return mailedPeople;
            }
        }

        return mailedPeople;
    }
}