package gr.athtech.backend.repository;

import gr.athtech.backend.model.User;
import gr.athtech.backend.model.FeedingSession;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.Duration;
import java.time.LocalDateTime;

public class TableCreator {
    public static void main(String[] args) {
        User admin = new User("daniel","dimitriou","admin");
        User parent = new User("daniel","dimitriou","parent");

        String start = "2023-06-04T10:30:00";
        String end = "2023-06-04T10:32:19";
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        Duration duration = Duration.between(startTime, endTime);
        long durationInSeconds = duration.getSeconds();

        long minutes = durationInSeconds / 60;
        long seconds = durationInSeconds % 60;

        String formattedDuration = String.format("%d:%02d", minutes, seconds);
        FeedingSession feedingSession = new FeedingSession(33,startTime,endTime,formattedDuration);
        feedingSession.setUser(parent);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.persist(parent);
        entityManager.persist(feedingSession);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}