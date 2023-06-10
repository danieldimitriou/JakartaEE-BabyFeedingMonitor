package gr.athtech.backend.responses;

import gr.athtech.backend.model.FeedingSession;

import java.util.List;

public class FeedingSessionListResponse {
    private List<FeedingSession> feedingSessionList;
    private double averageTime;
    private double averageAmountConsumed;


    public FeedingSessionListResponse(List<FeedingSession> feedingSessionList, double averageTime, double averageAmountConsumed) {
        this.feedingSessionList = feedingSessionList;
        this.averageTime = averageTime;
        this.averageAmountConsumed = averageAmountConsumed;
    }

    public List<FeedingSession> getFeedingSessionList() {
        return feedingSessionList;
    }

    public void setFeedingSessionList(List<FeedingSession> feedingSessionList) {
        this.feedingSessionList = feedingSessionList;
    }

    public double getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(double averageTime) {
        this.averageTime = averageTime;
    }

    public double getAverageAmountConsumed() {
        return averageAmountConsumed;
    }

    public void setAverageAmountConsumed(double averageAmountConsumed) {
        this.averageAmountConsumed = averageAmountConsumed;
    }
}
