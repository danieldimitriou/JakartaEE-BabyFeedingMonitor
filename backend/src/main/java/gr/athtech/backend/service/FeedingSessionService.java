package gr.athtech.backend.service;

import gr.athtech.backend.model.FeedingSession;

import javax.naming.NamingException;
import java.util.List;
import java.util.Optional;

public interface FeedingSessionService {
    Optional<FeedingSession> getFeedingSessionById(int id);
    Optional<List<FeedingSession>> getAll();
    boolean createFeedingSession(FeedingSession feedingSession) throws NamingException;
    Optional<FeedingSession> updateFeedingSession( FeedingSession newFeedingSessionData);


    boolean deleteFeedingSession(int id);
}
