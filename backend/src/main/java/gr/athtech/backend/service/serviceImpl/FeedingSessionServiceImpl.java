package gr.athtech.backend.service.serviceImpl;


import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.repository.FeedingSessionRepository;
import gr.athtech.backend.service.FeedingSessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

public class FeedingSessionServiceImpl implements FeedingSessionService {
    @Inject
    private FeedingSessionRepository feedingSessionRepository;
    private static final Logger logger = (Logger) LogManager.getLogger(FeedingSessionServiceImpl.class);

    @Override
    public Optional<FeedingSession> getFeedingSessionById(int id) {
        Optional<FeedingSession> feedingSession = this.feedingSessionRepository.getById(id);
        if (feedingSession.isPresent()) {
            return feedingSession;
        } else {
            throw new NotFoundException("Feeding session not found");
        }
    }

    @Override
    public Optional<List<FeedingSession>> getAll() {
        return this.feedingSessionRepository.getAll();
    }


    @Override
    public boolean createFeedingSession(FeedingSession feedingSession) throws NamingException {

//        JsonObject jsonObject = Json.createReader(new StringReader(jsonData)).readObject();
        feedingSession.calculateDuration();
        logger.error(feedingSession);
        return this.feedingSessionRepository.create(feedingSession);
    }

    @Override
    public Optional<FeedingSession> updateFeedingSession(FeedingSession newFeedingSessionData) {
        return this.feedingSessionRepository.update(newFeedingSessionData);
    }


    @Override
    public boolean deleteFeedingSession(int id) {
        boolean d = this.feedingSessionRepository.delete(id);
        logger.error("DELETE RESSSS:" + d);
        return d;
    }
}
