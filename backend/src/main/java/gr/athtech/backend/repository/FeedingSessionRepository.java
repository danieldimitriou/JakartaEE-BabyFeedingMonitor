package gr.athtech.backend.repository;

import gr.athtech.backend.model.FeedingSession;

import java.util.List;
import java.util.Optional;

public interface FeedingSessionRepository {
    Optional<FeedingSession> getById(int id);
    Optional<List<FeedingSession>> getAll();
    boolean create(FeedingSession feedingSession);
    Optional<FeedingSession> update(FeedingSession feedingSession);
    boolean delete(int id);

}
