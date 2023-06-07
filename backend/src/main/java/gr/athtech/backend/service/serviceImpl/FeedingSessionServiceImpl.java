package gr.athtech.backend.service.serviceImpl;


import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.repository.FeedingSessionRepository;
import gr.athtech.backend.service.FeedingSessionService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class FeedingSessionServiceImpl implements FeedingSessionService {
    @Inject
    private FeedingSessionRepository feedingSessionRepository;
    @Override
    public Optional<FeedingSession> getFeedingSessionById(int id) {
        Optional<FeedingSession> feedingSession = this.feedingSessionRepository.getById(id);
        return feedingSession;
    }

    @Override
    public Optional<List<FeedingSession>> getAll() {
        Optional<List<FeedingSession>> feedingSessions = this.feedingSessionRepository.getAll();
        return feedingSessions;
    }

    @Override
    public Optional<FeedingSession> createFeedingSession(String jsonData) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonData)).readObject();
        double amountConsumed = jsonObject.getJsonNumber("amountConsumed").doubleValue();
        LocalTime startTime = LocalTime.parse(jsonObject.getString("startTime"));
        LocalTime endTime = LocalTime.parse(jsonObject.getString("endTime"));
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        FeedingSession feedingSession = new FeedingSession(amountConsumed,startTime, endTime, date);
        boolean d = this.feedingSessionRepository.create(feedingSession);
        if(d){
            return Optional.of(feedingSession);
        }else{
            return Optional.empty();
        }
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
