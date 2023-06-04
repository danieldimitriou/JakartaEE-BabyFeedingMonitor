package gr.athtech.backend.repository.repositoryImpl;
import gr.athtech.backend.model.Account;
import gr.athtech.backend.model.Role;
import gr.athtech.backend.repository.AccountRepository;
import gr.athtech.backend.resource.AccountResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class AccountRepositoryImpl implements AccountRepository {
    private    static final String DB_URL = "jdbc:postgresql://localhost:5432/BabyFeedingMonitor";
    private  static final String USER = "postgres";
    private  static final String PASS = "password";
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final Logger logger = LogManager.getLogger(AccountResource.class);
    private EntityManager entityManager;

    @Override
    public Account getAccountById(Long id) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String selectCommand = "SELECT * FROM account WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             PreparedStatement selectStmt = conn.prepareStatement(selectCommand)) {
            // Create the table if it doesn't exist
            selectStmt.setLong(1, id);
            ResultSet resultSet = selectStmt.executeQuery();

            if (resultSet.next()) {
                // Retrieve values from the result set and create an Account object
                Long accountId = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String roleString = resultSet.getString("role");
                Role role = Role.valueOf(roleString);
                // Retrieve other columns as needed

                Account account = new Account(accountId, firstName, lastName, role);
                // Set other properties of the account object as needed

                return account;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
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
                long generatedId = generatedKeys.getLong(1);
                Long accountId = Long.valueOf(generatedId);
                account.setId(accountId);
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
    public boolean deleteAccount(Long id) {
        return false;
    }
}
