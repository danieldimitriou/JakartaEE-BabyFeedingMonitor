package gr.athtech.backend.repository;

import gr.athtech.backend.model.FeedingSession;

import javax.naming.NamingException;
import java.util.List;
import java.util.Optional;

public interface FeedingSessionRepository {
    Optional<FeedingSession> getById(int id);
    Optional<List<FeedingSession>> getAll();
    boolean create(FeedingSession feedingSession) throws NamingException;
    Optional<FeedingSession> update(FeedingSession feedingSession);
    boolean delete(int id);

}
