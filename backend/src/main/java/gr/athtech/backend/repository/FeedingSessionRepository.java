package gr.athtech.backend.repository;

import gr.athtech.backend.model.FeedingSession;

import java.util.Optional;

public interface FeedingSessionRepository {
    Optional<FeedingSession> getFeedingSessionById(int id);
    void createFeedingSession(FeedingSession feedingSession);
    Optional<FeedingSession> updateFeedingSession(FeedingSession feedingSession);
    boolean deleteFeedingSession(int id);

}
