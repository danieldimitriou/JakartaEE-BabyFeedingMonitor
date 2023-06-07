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
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class FeedingSessionRepositoryImpl implements FeedingSessionRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    @Override
    public Optional<FeedingSession> getById(int id) {
        entityManager.find(User.class,id);
        return Optional.empty();
    }
    @Override
    public Optional<List<FeedingSession>> getAll() {
        entityManager.find(User.class, getAll());
        return Optional.empty();
    }


    @Override
    public boolean create(FeedingSession feedingSession) {
        try {
            this.entityManager.persist(feedingSession);
            return true; // Entity saved successfully
        } catch (Exception e) {
            // Handle any exceptions or errors that occurred during persistence
            e.printStackTrace();
            return false; // Failed to save the entity
        }
    }

    @Override
    public Optional<FeedingSession> update(FeedingSession feedingSession) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
