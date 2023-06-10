package gr.athtech.backend;
import gr.athtech.backend.dto.FeedingSessionListDTO;
import gr.athtech.backend.model.FeedingSession;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.*;

public class Database {
    private static final Logger logger = (Logger) LogManager.getLogger(Database.class);

    @PersistenceUnit(unitName = "default")
    static EntityManagerFactory entityManagerFactory;

    private Database() {
        // Private constructor to prevent instantiation
    }

    //get the entity manager factory. If it is not open, open it and return it. Follows a singleton approach, only one instance is used.
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            synchronized (Database.class) {
                if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
                    Map<String, String> persistenceProperties = new HashMap<>();
                    persistenceProperties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
                    persistenceProperties.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/BabyFeedingMonitor");
                    persistenceProperties.put("javax.persistence.jdbc.user", "postgres");
                    persistenceProperties.put("javax.persistence.jdbc.password", "password");
                    persistenceProperties.put("jakarta.persistence.schema-generation.database.action", "create");
                    entityManagerFactory = Persistence.createEntityManagerFactory("default", persistenceProperties);
                }
            }
        }
        return entityManagerFactory;
    }
    public static <T> boolean persist(T entity) throws Exception, PersistenceException {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager =  entityManagerFactory.createEntityManager();
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
    public static <T> Optional<T> update(T entity) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T updatedEntity = entityManager.merge(entity);
            transaction.commit();
            return Optional.ofNullable(updatedEntity);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update entity", e);
        } finally {
            entityManager.close();
        }
    }

    public static <T> Optional<T> queryById(Class<T> entityClass, int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            T entity = entityManager.find(entityClass, id);
            return Optional.ofNullable(entity);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static <T> Optional<List<T>> queryAll(Class<T> entityClass) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            String queryStr = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = entityManager.createQuery(queryStr, entityClass);
            List<T> resultList = query.getResultList();
            return Optional.of(resultList);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    public static Optional<FeedingSessionListDTO> queryAllFeedingSessions(Class<FeedingSession> entityClass) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            String queryStr = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<FeedingSession> query = entityManager.createQuery(queryStr, entityClass);
            List<FeedingSession> resultList = query.getResultList();

            FeedingSessionListDTO dto = new FeedingSessionListDTO(resultList);
            return Optional.of(dto);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


    public static <T> boolean delete(int id, Class<T> entityClass) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // Find the entity by ID
            T entity = entityManager.find(entityClass, id);

            // Check if the entity exists
            if (entity != null) {
                // Remove the entity
                entityManager.remove(entity);
                transaction.commit();
                return true;
            } else {
                // Entity not found
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }
    public static <T> T executeQuery(String sqlQuery, Class<T> entityClass, List<String> parameters, Object... parameterValues) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            TypedQuery<T> query = entityManager.createQuery(sqlQuery, entityClass);
            for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(parameters.get(i), parameterValues[i]);
            }
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if no result found
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }
    public static <T> List<T> executeListQuery(String jpqlQuery, Class<T> entityClass, Object... parameters) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            TypedQuery<T> query = entityManager.createQuery(jpqlQuery, entityClass);

            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i + 1, parameters[i]);
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly, e.g., log the error
            return Collections.emptyList();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }



}
