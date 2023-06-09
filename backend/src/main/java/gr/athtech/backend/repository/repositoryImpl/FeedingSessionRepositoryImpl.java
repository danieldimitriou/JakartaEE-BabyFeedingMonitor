package gr.athtech.backend.repository.repositoryImpl;

import gr.athtech.backend.Database;
import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.model.User;
import gr.athtech.backend.repository.FeedingSessionRepository;
import jakarta.persistence.*;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class FeedingSessionRepositoryImpl implements FeedingSessionRepository {

//    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    @Override
    public Optional<FeedingSession> getById(int id) {
        try {
            return Database.queryById(FeedingSession.class, id);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly, e.g., log the error
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<FeedingSession>> getAll() {
        try {
            return Database.queryAll(FeedingSession.class);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly, e.g., log the error
            return Optional.empty();
        }
    }


    @Override
    public boolean create(FeedingSession feedingSession) {
        try {
            return Database.persist(feedingSession);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<FeedingSession> update(FeedingSession feedingSession) {
        try {
            return Database.update(feedingSession);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return Database.delete(id, FeedingSession.class);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
