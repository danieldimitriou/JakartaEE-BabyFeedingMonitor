package gr.athtech.backend.repository;

import gr.athtech.backend.model.LoginResponseData;
import gr.athtech.backend.model.User;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.Optional;


public interface UserRepository {


    Optional<User> getUserById(Long id) throws SQLException;
    boolean createUser(User user);
    Optional<User> updateUser(User user);
    boolean deleteUser(Long id);
    Optional<User> getUserByEmail(String email);
    LoginResponseData login(String email, String password) throws LoginException;
    Optional<User> authenticateUser(String email, String password);
}
