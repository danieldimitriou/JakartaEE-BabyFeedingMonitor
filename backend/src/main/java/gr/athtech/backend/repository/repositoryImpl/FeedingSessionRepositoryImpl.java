package gr.athtech.backend.repository.repositoryImpl;

import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.model.User;
import gr.athtech.backend.repository.FeedingSessionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class FeedingSessionRepositoryImpl implements FeedingSessionRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    @Override
    public Optional<FeedingSession> getFeedingSessionById(int id) {
        entityManager.find(User.class,id);
        return Optional.empty();
    }

    @Override
    public void createFeedingSession(FeedingSession feedingSession) {


//        Duration duration = Duration.between(feedingSession.getStartTime(), feedingSession.getEndTime());
//        long durationInSeconds = duration.getSeconds();
//
//        long minutes = durationInSeconds / 60;
//        long seconds = durationInSeconds % 60;
//
//        String formattedDuration = String.format("%d:%02d", minutes, seconds);
//        feedingSession.setDuration(formattedDuration);
        this.entityManager.persist(feedingSession);
    }

    @Override
    public Optional<FeedingSession> updateFeedingSession(FeedingSession feedingSession) {
        return Optional.empty();
    }

    @Override
    public boolean deleteFeedingSession(int id) {
        return false;
    }
}
