package gr.athtech.backend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column
    @NotNull
    double amountConsumed;
    @Column
    @NotNull
    LocalDateTime startTime;
    @Column
    @NotNull
    LocalDateTime endTime;
    //the account of the parent logging the information

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Account parent;

    public FeedingSession(double amountConsumed, LocalDateTime startTime, LocalDateTime endTime, Account parent){
        this.amountConsumed = amountConsumed;
        this.startTime = startTime;
        this.endTime = endTime;
        this.parent = parent;
    }
}
