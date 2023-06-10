package gr.athtech.backend.repository;

import gr.athtech.backend.model.User;
import gr.athtech.backend.model.FeedingSession;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class TableCreator {
    private static final Logger logger = LogManager.getLogger(TableCreator.class);

    public static void main(String[] args) {
        User admin = new User("daniel", "dimitriou", "admin", "a@a.com", "1");
        User parent = new User("daniel", "dimitriou", "physician", "b@a.com", "1");

        // Start date and time
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JUNE, 4, 10, 0, 0); // Set the initial start date and time
        java.util.Date startDate = calendar.getTime();

        List<FeedingSession> feedingSessions = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 15; i++) {
            // Generate random duration between 10 and 20 minutes
            int durationMinutes = 10 + (int) (Math.random() * 11);

            // Create feeding session
            FeedingSession feedingSession = new FeedingSession();
            feedingSession.setAmountConsumed(rand.nextInt(50));
            feedingSession.setStartTime(new Time(calendar.getTimeInMillis()));
            calendar.add(Calendar.MINUTE, durationMinutes);
            feedingSession.setEndTime(new Time(calendar.getTimeInMillis()));
            feedingSession.setDate(new Date(calendar.getTimeInMillis()));
            feedingSession.setUser(parent);
            feedingSession.calculateDuration();

            feedingSessions.add(feedingSession);

            // Increment date and time for the next session
            calendar.add(Calendar.MINUTE, 10); // Add 10 minutes between sessions
            if (i % 3 == 2) {
                calendar.add(Calendar.DAY_OF_MONTH, 1); // Add 1 day after every 3 sessions
                calendar.set(Calendar.HOUR_OF_DAY, 10); // Reset the hour to 10
                calendar.set(Calendar.MINUTE, 0); // Reset the minutes to 0
            }
        }

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // Persist the feeding sessions
        for (FeedingSession feedingSession : feedingSessions) {
            entityManager.persist(feedingSession);
        }

        entityManager.persist(admin);
        entityManager.persist(parent);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
