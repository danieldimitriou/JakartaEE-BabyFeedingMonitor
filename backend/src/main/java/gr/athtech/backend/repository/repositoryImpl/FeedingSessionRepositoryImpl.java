package gr.athtech.backend.repository.repositoryImpl;

import gr.athtech.backend.Database;
import gr.athtech.backend.dto.FeedingSessionListDTO;
import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.model.User;
import gr.athtech.backend.repository.FeedingSessionRepository;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class FeedingSessionRepositoryImpl implements FeedingSessionRepository {

    private static final Logger logger = LogManager.getLogger(FeedingSessionRepositoryImpl.class);

    @Override
    public Optional<FeedingSession> getById(int id) {
        try {
            return Database.queryById(FeedingSession.class, id);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly, e.g., log the error
            return Optional.empty();
        }
    }


    @Override
    public Optional<FeedingSessionListDTO> getAll() {
        try {
            return Database.queryAllFeedingSessions(FeedingSession.class);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly, e.g., log the error
            return Optional.empty();
        }
    }


    @Override
    public boolean create(FeedingSession feedingSession) {
        try {
            return Database.persist(feedingSession);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<FeedingSession> update(FeedingSession feedingSession) {
        try {
            return Database.update(feedingSession);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return Database.delete(id, FeedingSession.class);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<FeedingSessionListDTO> getByDates(Date startDate, Date endDate) {
        try {
            String jpql = "SELECT e FROM FeedingSession e WHERE e.date BETWEEN ?1 AND ?2";

            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            List<FeedingSession> feedingSessions = Database.executeListQuery(jpql, FeedingSession.class, sqlStartDate, sqlEndDate);
            logger.error(feedingSessions);
            FeedingSessionListDTO feedingSessionListDTO = new FeedingSessionListDTO(feedingSessions);

            return Optional.of(feedingSessionListDTO);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly, e.g., log the error
            return Optional.empty();
        }
    }

}
