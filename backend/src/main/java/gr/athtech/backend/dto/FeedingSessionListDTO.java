package gr.athtech.backend.dto;

import gr.athtech.backend.model.FeedingSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedingSessionListDTO {
    private List<FeedingSession> feedingSessions;

    public List<FeedingSession> getFeedingSessions() {
        return feedingSessions;
    }

    public void setFeedingSessions(List<FeedingSession> feedingSessions) {
        this.feedingSessions = feedingSessions;
    }

    public double getAverageTime(){
        double average = 0;
        for(FeedingSession feedingSession: feedingSessions){
            average+= feedingSession.getDuration();
        }
        return average/feedingSessions.size();
    }
    public double getAverageAmountConsumed(){
        double average = 0;
        for(FeedingSession feedingSession: feedingSessions){
            average+= feedingSession.getAmountConsumed();
        }
        return average/feedingSessions.size();
    }

    public void addFeedingSession(FeedingSession feedingSessionDTO) {
        this.feedingSessions.add(feedingSessionDTO);
    }

    public int getSize(){
        return this.feedingSessions.size();
    }
}
