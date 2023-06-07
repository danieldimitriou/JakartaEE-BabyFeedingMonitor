package gr.athtech.backend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "feeding_session")
public class FeedingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //feeding session information
    @Column(name = "amount_consumed")
    private double amountConsumed;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "date")
    private LocalDate date;
    //the account of the parent logging the information
    @Column(name = "duration")
    private Long duration;
    @ManyToOne
    private User user;


    public FeedingSession(double amountConsumed, LocalTime startTime, LocalTime endTime, LocalDate date){
        this.amountConsumed = amountConsumed;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        //calculate the duration of the feeding session
        Duration dur = Duration.between(startTime, endTime);
        this.duration = dur.toMinutes();
    }
    public FeedingSession(double amountConsumed, LocalTime startTime, LocalTime endTime, LocalDate date, Long duration){
        this.amountConsumed = amountConsumed;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.date = date;
    }
}
