package gr.athtech.backend.repository;

import gr.athtech.backend.model.Account;
import gr.athtech.backend.model.FeedingSession;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

public interface FeedingSessionRepository {
    Optional<FeedingSession> getFeedingSessionById(int id);
    void createFeedingSession(FeedingSession feedingSession);
    Optional<FeedingSession> updateFeedingSession(FeedingSession feedingSession);
    boolean deleteFeedingSession(int id);

}
