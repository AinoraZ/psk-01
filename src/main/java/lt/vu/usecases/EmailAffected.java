package lt.vu.usecases;

import lombok.Getter;
import lt.vu.interceptors.CaughtInvocation;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.EmailingService;
import lt.vu.services.EmailingServiceImpl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class EmailAffected implements Serializable {
    @Inject @Getter
    EmailingService emailingService;
    private Map<Integer, CompletableFuture<Integer>> emailingServiceTasks = new HashMap<>();

    @LoggedInvocation
    public void sendEmail(Integer playerId) {
        emailingServiceTasks.put(playerId, CompletableFuture.supplyAsync(() -> emailingService.sendEmailsToContacting(playerId)));
    }

    public String getEmailingServiceStatus(Integer playerId) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> emailingServiceTask = emailingServiceTasks.get(playerId);
        if (emailingServiceTask == null)
            return null;

        if (emailingServiceTask != null && !emailingServiceTask.isDone())
            return "Mailing service in progress";

        return "Mailing succeeded, mailed " + emailingServiceTask.get() + " people";
    }
}
