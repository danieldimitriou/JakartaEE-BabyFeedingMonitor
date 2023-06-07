package gr.athtech.backend.service;

import gr.athtech.backend.model.FeedingSession;

import java.util.List;
import java.util.Optional;

public interface FeedingSessionService {
    Optional<FeedingSession> getFeedingSessionById(int id);
    Optional<List<FeedingSession>> getAll();
    Optional<FeedingSession> createFeedingSession(String jsonData);
    Optional<FeedingSession> updateFeedingSession(FeedingSession feedingSession);
    boolean deleteFeedingSession(int id);
}
