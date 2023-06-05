package gr.athtech.backend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feeding_session")
public class FeedingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    //feeding session information
    @Column(name = "amount_consumed")
    double amountConsumed;
    @Column(name = "start_time")
    LocalDateTime startTime;
    @Column(name = "end_time")
    LocalDateTime endTime;
    //the account of the parent logging the information
    @Column(name = "duration")
    String duration;
    @ManyToOne
    User user;


    public FeedingSession(double amountConsumed, LocalDateTime startTime, LocalDateTime endTime){
        this.amountConsumed = amountConsumed;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public FeedingSession(double amountConsumed, LocalDateTime startTime, LocalDateTime endTime, String duration){
        this.amountConsumed = amountConsumed;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
}
