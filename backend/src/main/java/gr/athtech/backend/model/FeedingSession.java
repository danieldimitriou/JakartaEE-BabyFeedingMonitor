package gr.athtech.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.Duration;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "feeding_session")
public class FeedingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Feeding session information
    @Column(name = "amount_consumed")
    private double amountConsumed;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "date")
    private Date date;

    // The account of the parent logging the information
    @Column(name = "duration")
    private long duration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FeedingSession(double amountConsumed, Time startTime, Time endTime, Date date) {
        this.amountConsumed = amountConsumed;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        calculateDuration();
    }

    public void calculateDuration() {
        long startTimeMillis = startTime.getTime();
        long endTimeMillis = endTime.getTime();
        long durationMillis = endTimeMillis - startTimeMillis;
        long durationMinutes = durationMillis / (1000 * 60);  // Convert milliseconds to minutes
        this.duration = Duration.ofMinutes(durationMinutes).toMinutes();
    }

//    public String getDurationAsString() {
//        long durationMinutes = duration.toMinutes();
//        return durationMinutes + " minutes";
//    }

}
