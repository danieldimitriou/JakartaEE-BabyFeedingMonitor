package gr.athtech.backend.repository.repositoryImpl;

import gr.athtech.backend.model.Account;
import gr.athtech.backend.repository.AccountRepository;
import gr.athtech.backend.resource.AccountResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.tool.schema.spi.ExecutionOptions;

import java.sql.*;
import java.util.EnumSet;
import java.util.Optional;

@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository {
    private    static final String DB_URL = "jdbc:postgresql://localhost:5432/BabyFeedingMonitor";
    private  static final String USER = "postgres";
    private  static final String PASS = "password";
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final Logger logger = LogManager.getLogger(AccountResource.class);

    @Override
    public Optional<Account> getAccountById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void createAccount(Account account) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String createTableCommand = "CREATE TABLE IF NOT EXISTS account (" +
                "id SERIAL PRIMARY KEY," +
                "firstName VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50) NOT NULL," +
                "role VARCHAR(50) NOT NULL" +
                ");";

        String insertCommand = "INSERT INTO account (firstName, lastName, role) VALUES (?, ?, ?);";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             PreparedStatement insertStmt = conn.prepareStatement(insertCommand,
                     Statement.RETURN_GENERATED_KEYS)) {

            // Create the table if it doesn't exist
            stmt.executeUpdate(createTableCommand);

            insertStmt.setString(1, account.getFirstName());
            insertStmt.setString(2, account.getLastName());
            insertStmt.setString(3, account.getRole());
            insertStmt.execute();

            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                account.setId(generatedId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Account> updateAccount(Account account) {
        return Optional.empty();
    }

    @Override
    public boolean deleteAccount(int id) {
        return false;
    }
}
