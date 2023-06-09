package gr.athtech.backend.repository;

import gr.athtech.backend.model.User;
import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.repository.repositoryImpl.UserRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TableCreator {
    private static final Logger logger = LogManager.getLogger(TableCreator.class);

    public static void main(String[] args) {
        User admin = new User("daniel","dimitriou","admin","email@email.com","pas");
        User parent = new User("daniel","dimitriou","physician","email@emai1l.com", "pass");

        String start = "2023-06-04T10:30:00";
        String end = "2023-06-04T10:32:19";
//        LocalDateTime startTime = LocalDateTime.parse(start);
//        LocalDateTime endTime = LocalDateTime.parse(end);
//        LocalTime startTime = LocalTime.of(10, 0);
//        LocalTime endTime = LocalTime.of(11, 32);
//        Duration duration = Duration.between(startTime, endTime);
//        Date feedingDate = Date
//        FeedingSession feedingSession = new FeedingSession(33.0, startTime,endTime,
//                feedingDate);
//        feedingSession.setUser(parent);
//        logger.error("FeedingSession(id={}, amountConsumed={}, startTime={}, endTime={}, date={}, duration={}, user={})",
//                feedingSession.getId(), feedingSession.getAmountConsumed(), feedingSession.getStartTime(),
//                feedingSession.getEndTime(), feedingSession.getDate(), feedingSession.getDuration(),
//                feedingSession.getUser());
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.persist(parent);
//        entityManager.persist(feedingSession);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}