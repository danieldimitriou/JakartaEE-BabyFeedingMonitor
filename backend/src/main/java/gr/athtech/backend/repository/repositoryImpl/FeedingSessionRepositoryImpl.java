package gr.athtech.backend.repository.repositoryImpl;

import gr.athtech.backend.model.Account;
import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.repository.FeedingSessionRepository;
import gr.athtech.backend.resource.AccountResource;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Optional;

@ApplicationScoped
public class FeedingSessionRepositoryImpl implements FeedingSessionRepository {
    private    static final String DB_URL = "jdbc:postgresql://localhost:5432/BabyFeedingMonitor";
    private  static final String USER = "postgres";
    private  static final String PASS = "password";
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final Logger logger = LogManager.getLogger(AccountResource.class);
    @Override
    public Optional<FeedingSession> getFeedingSessionById(int id) {
        return Optional.empty();
    }

    @Override
    public void createFeedingSession(FeedingSession feedingSession) {
//        try {
//            Class.forName(JDBC_DRIVER);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        String createTableCommand = "CREATE TABLE IF NOT EXISTS feeding_session (" +
//                "id SERIAL PRIMARY KEY, " +
//                "amountConsumed DOUBLE NOT NULL, " +
//                "startTime DATETIME NOT NULL, " +
//                "endTime DATETIME NOT NULL, " +
//                "parent_id INT, " +
//                "FOREIGN KEY (parent_id) REFERENCES Account(id)" +
//                ");";
//
//
//        String insertCommand = "INSERT INTO feeding_session (amountConsumed, startTime, endTime, parent_id) VALUES (?, ?, ?, ?, ?);";
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement stmt = conn.createStatement();
//             PreparedStatement insertStmt = conn.prepareStatement(insertCommand,
//                     Statement.RETURN_GENERATED_KEYS)) {
//
//            // Create the table if it doesn't exist
//            stmt.executeUpdate(createTableCommand);
//
//            insertStmt.setDouble(1, feedingSession.getAmountConsumed());
//            insertStmt.setDate(2, feedingSession.getStartTime());
//            insertStmt.setString(3, account.getRole());
//            insertStmt.execute();
//
//            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                int generatedId = generatedKeys.getInt(1);
//                account.setId(generatedId);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//
//
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
