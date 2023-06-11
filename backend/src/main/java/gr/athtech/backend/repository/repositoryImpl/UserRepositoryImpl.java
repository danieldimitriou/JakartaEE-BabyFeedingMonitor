package gr.athtech.backend.repository.repositoryImpl;
import gr.athtech.backend.Database;
import gr.athtech.backend.JWTGenerator;
import gr.athtech.backend.model.LoginResponseData;
import gr.athtech.backend.model.User;
import gr.athtech.backend.repository.UserRepository;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.security.auth.login.LoginException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);
    @Override

    public Optional<User> getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        logger.error(user);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean createUser(User user) {
        try {
            return Database.persist(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<User> updateUser(User user) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        String query = "SELECT u FROM User u WHERE u.email = :email";
        try {
            List<String> parameterList = new ArrayList<>();
            parameterList.add("email");
            return Optional.ofNullable(Database.executeQuery(query, User.class, parameterList, email));
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception properly, e.g., log the error
            return Optional.empty();
        }
    }

    //very simple login
    @Override
    public LoginResponseData login(String email, String password) throws LoginException {
        logger.error("Repo login");
        Optional<User> user = this.getUserByEmail(email);
        logger.error(user);
        if(user.get().getPassword().equals(password)){
            String jwt = JWTGenerator.generateToken(email, user.get().getRole().toString());
            return new LoginResponseData(jwt, user.get().getRole().toString(),  200);
        }else{
            logger.error("else");
            throw new LoginException("Invalid email or password");
        }
    }
    @Override
    public Optional<User> authenticateUser(String email, String password){
        Optional<User> user = this.getUserByEmail(email);
        if(user.get().getPassword().equals(password)){
            return user;
        }else{
            return Optional.empty();
        }
    }
}
